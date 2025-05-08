package de.simone.ui;

import java.util.*;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.checkbox.*;
import com.vaadin.flow.component.confirmdialog.*;
import com.vaadin.flow.component.dialog.*;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.*;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.renderer.*;
import com.vaadin.flow.function.*;
import com.vaadin.flow.router.*;

import de.simone.*;
import de.simone.backend.*;
import de.simone.ui.components.*;
import jakarta.annotation.security.*;
import jakarta.inject.*;
import jakarta.ws.rs.core.*;

@RolesAllowed({ "Sitrone.master", "Users" })
@Route(value = "Users", layout = MainLayout.class)
public class UsersView extends TAView<User> {

    @Inject
    UsersService usersService;

    @Inject
    ExternalLoginService externalLoginService;

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
                        te -> {
                            return new Span();

                            // MovilListItem mli = new MovilListItem(ge.isEnabled, ge.firstName + " " +
                            // ge.lastName,
                            // ge.email, null, null);
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
                new ComponentRenderer<>(te -> UIUtils.getBooleanBadge(te.isEnabled, true, false)))
                .setHeader(getTranslation("User.isEnabled"))
                .setComparator(te -> te.isEnabled)
                .setAutoWidth(true);

        grid.addColumn(te -> UIUtils.getFormatedLocalDateTime(te.lastSignIn))
                .setComparator(te -> te.lastSignIn)
                .setHeader(getTranslation("User.lastSignIn"))
                .setTextAlign(ColumnTextAlign.END)
                .setAutoWidth(true);

        newPassword = UIUtils.getPasswordField("ResetPassword.passwordNew");
        passwordConfirmation = UIUtils.getPasswordField("ResetPassword.passwordConfirmation");
        acceptPassword = UIUtils.getButton("ResetPassword.accept", ButtonVariant.LUMO_PRIMARY);
        acceptPassword.addClickListener(evt -> resetPassword());
        isTemporalPass = UIUtils.getCheckbox("User.isTemporalPass");

        this.passwodrResetDialog = UIUtils.getDialog(
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
            Response response = usersService.setNewPassword(user, newPassword.getValue(), isTemporalPass.getValue());
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
        Icon passwordReset = UIUtils.getAfterIcon(
                MaterialIcons.CLEANING_SERVICES.create(), PASSWORD_RESET, UIUtils.EDIT_ACTION);
        UIUtils.getElementToolBar(this, passwordReset);
        setToolBarComponents(UIUtils.getToolBar(this));
        removeToolBarComponents(UIUtils.DUPLICATE_ACTION);
        addOneTimeTip(HanniTask.TIP_USERS);

        SerializablePredicate<User> filter = entity -> {
            String searchTerm = searchField.getValue().trim().toLowerCase();
            if (searchTerm.isEmpty())
                return true;

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
                if (selectedItem == null)
                    return;

                ConfirmDialog dialog = UIUtils.getDeleteDialog(taEntity);
                dialog.addConfirmListener(
                        e -> {
                            Response response = usersService.delete(selectedItem.id, 0L);
                            if (UIUtils.showNotification(response))
                                UI.getCurrent().navigate(viewUrl);
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
            if (user == null)
                return;

            newPassword.setValue("");
            passwordConfirmation.setValue("");
            isTemporalPass.setValue(true);
            passwodrResetDialog.open();
            return;
        }

        super.onComponentEvent(event);
    }
}
