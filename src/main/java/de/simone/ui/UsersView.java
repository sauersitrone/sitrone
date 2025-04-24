package de.simone.ui;

import java.util.Arrays;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.backend.ExternalLoginService;
import de.simone.backend.HanniTask;
import de.simone.backend.User;
import de.simone.backend.UsersService;
import de.simone.ui.components.MaterialIcons;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@RolesAllowed({"Sitrone.master", "Users"})
@Route(value = "Users", layout = MainLayout.class)
public class UsersView extends TAView<User> {

  @Inject UsersService usersService;

  @Inject ExternalLoginService externalLoginService;

  public static final String PASSWORD_RESET = "action.passwordReset";

  private User user;
  private Dialog passwodrResetDialog;

  // reset passwort dialog
  private PasswordField newPassword;
  private PasswordField passwordConfirmation;
  private Button acceptPassword;
  private Checkbox isTemporalPass;
  private User currentUser;

  public UsersView() {
    this.grid = UIUtils.getGrid(User.class);

    // mobile
    grid.addColumn(
            new ComponentRenderer<>(
                ge -> {
                  return new Span();

                  // MovilListItem mli = new MovilListItem(ge.isEnabled, ge.firstName + " " +
                  // ge.lastName,
                  //         ge.email, null, null);
                  // mli.setAvatar(ge.firstName + " " + ge.lastName, ge.avatar);
                  // return mli;
                }))
        .setHeader(getTranslation("User.fullName"))
        .setComparator(te -> te.firstName + " " + te.lastName)
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(te -> te.userName)
        .setComparator(te -> te.userName)
        .setHeader(getTranslation("User.userName"))
        .setAutoWidth(true);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getUserRender))
        .setHeader(getTranslation("User.fullName"))
        .setComparator(te -> te.firstName + " " + te.lastName)
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.email)
        .setComparator(te -> te.email)
        .setHeader(getTranslation("User.email"))
        .setAutoWidth(true);

    grid.addColumn(
            new ComponentRenderer<>(ge -> UIUtils.getBooleanBadge(ge.isEnabled, true, false)))
        .setHeader(getTranslation("User.isEnabled"))
        .setComparator(te -> te.isEnabled)
        .setAutoWidth(true);

    grid.addColumn(ge -> UIUtils.getFormatedLocalDateTime(ge.lastSignIn))
        .setComparator(te -> te.lastSignIn)
        .setHeader(getTranslation("User.lastSignIn"))
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);

    newPassword = UIUtils.getPasswordField("ResetPassword.passwordNew");
    passwordConfirmation = UIUtils.getPasswordField("ResetPassword.passwordConfirmation");
    acceptPassword = UIUtils.getButton("ResetPassword.accept", ButtonVariant.LUMO_PRIMARY);
    acceptPassword.addClickListener(evt -> resetPassword());
    isTemporalPass = UIUtils.getCheckbox("User.isTemporalPass");

    this.passwodrResetDialog =
        UIUtils.getDialog(
            "ResetPassword.title",
            "ResetPassword.message",
            Arrays.asList(acceptPassword),
            "ResetPassword.cancel",
            newPassword,
            passwordConfirmation,
            isTemporalPass);
    passwodrResetDialog.setWidth("20%");
    passwodrResetDialog.setHeight("60%");
  }

  private void resetPassword() {
    if (UIUtils.confirmPasswordFields(null, newPassword, passwordConfirmation)) {
      Response response =
          usersService.setNewPassword(user, newPassword.getValue(), isTemporalPass.getValue());
      if (UIUtils.showResponseInFields(response, newPassword, passwordConfirmation)) {
        passwodrResetDialog.close();
        UIUtils.showNotification(response);
      }
    }
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    init(User.class, UserForm.class, usersService, false);
    currentUser = SecurityUtils.getLoggedUser();
    Icon passwordReset =
        UIUtils.getAfterIcon(
            MaterialIcons.CLEANING_SERVICES.create(), PASSWORD_RESET, UIUtils.EDIT_ACTION);
    UIUtils.getElementToolBar(this, passwordReset);
    setToolBarComponents(UIUtils.getToolBar(this));
    removeToolBarComponents(UIUtils.DUPLICATE_ACTION);
    addOneTimeTip(HanniTask.TIP_USERS);

    SerializablePredicate<User> filter =
        entity -> {
          String searchTerm = searchField.getValue().trim().toLowerCase();
          if (searchTerm.isEmpty()) return true;

          boolean m1 = entity.firstName.toLowerCase().contains(searchTerm);
          boolean m2 = entity.lastName.toLowerCase().contains(searchTerm);
          boolean m3 = entity.email.toLowerCase().contains(searchTerm);
          boolean m4 = entity.userName.toLowerCase().contains(searchTerm);

          return m1 || m2 || m3 || m4;
        };

    setItems(usersService.listAll(), filter);
  }

  @Override
  public void onComponentEvent(ClickEvent<Button> event) {
    Button button = event.getSource();
    String cmpId = button.getId().orElse("null");

    // only check for auth for new user
    if (cmpId.startsWith(UIUtils.NEW_ACTION)) {
      UIUtils.showErrorNotification("Auth.error.newUser");
      return;
    }

    if (cmpId.startsWith(UIUtils.DELETE_ACTION)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".delete");
      if (ok) {

        User selectedItem = getSelectedItem();
        if (selectedItem == null) return;

        ConfirmDialog dialog = UIUtils.getDeleteDialog(taEntity);
        dialog.addConfirmListener(
            e -> {
              Response response = usersService.delete(selectedItem.id, 0L);
              if (UIUtils.showNotification(response)) UI.getCurrent().navigate(viewUrl);
            });
        dialog.open();
        return;
      } else {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
        return;
      }
    }

    if (cmpId.startsWith(PASSWORD_RESET)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".password.reset");
      if (!ok) {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
        return;
      }

      user = getSelectedItem();
      if (user == null) return;

      newPassword.setValue("");
      passwordConfirmation.setValue("");
      isTemporalPass.setValue(true);
      passwodrResetDialog.open();
      return;
    }

    super.onComponentEvent(event);
  }
}
