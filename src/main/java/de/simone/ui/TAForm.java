package de.simone.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveEvent.ContinueNavigationAction;
import com.vaadin.flow.router.BeforeLeaveObserver;

import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.backend.TAEntity;
import de.simone.backend.TAService;
import de.simone.backend.UsersService;
import io.quarkus.logging.Log;
import jakarta.ws.rs.core.Response;

public abstract class TAForm<E extends TAEntity> extends VerticalLayout
    implements ComponentEventListener<ClickEvent<Button>>, BeforeLeaveObserver, HastEntity<E> {

  public static final String DIALOG = "DIALOG";
  public static final String DETAIL = "DETAIL";

  private HorizontalLayout footerLayout;
  private TAEntity taEntity;
  private List<String> onSuccessTasks;
  private boolean confirmBeforeLeave;
  private boolean hasChanged = false;
  private List<Component> footerComponents;
  private List<Component> bodyComponents;

  protected TAView<E> taView;
  protected Dialog detailDialog;
  protected TAService<E> taService;
  protected String view = DIALOG;
  protected BeanValidationBinder<E> binder;

  protected TAForm() {
    confirmBeforeLeave = true;

    this.bodyComponents = new ArrayList<>();
    this.footerComponents = UIUtils.getCRUDButtons(this);
    footerLayout = UIUtils.getCRUDFooter(this);

    add(footerLayout);

    setAlignItems(Alignment.STRETCH);
    setPadding(false);
    setSpacing(false);
    setMargin(false);
    setSizeFull();
  }

  protected void addBodyComponets(Component... childrens) {
    addBodyComponets(null, false, childrens);
  }

  protected void addBodyComponets(String titleId, Component... childrens) {
    addBodyComponets(titleId, false, childrens);
  }

  protected void addBodyComponets(String titleId, boolean withHelp, Component... childrens) {
    // FormLayout formLayout = new FormLayout(childrens);
    // formLayout.addClassName(LumoUtility.Background.CONTRAST_10);
    // components.add(formLayout);

    if (titleId != null) bodyComponents.add(UIUtils.getTitleH(titleId, withHelp));
    bodyComponents.addAll(Arrays.asList(childrens));
  }

  public void onSuccess(String... onSuccessTasks) {
    this.onSuccessTasks = Arrays.asList(onSuccessTasks);
  }

  public void setFooterComponents(List<Component> buttons) {
    footerComponents = buttons;
    footerLayout.removeAll();
    footerLayout.add(buttons);
  }

  public List<Component> getBodyComponents() {
    return bodyComponents;
  }

  public List<Component> getFooterComponents() {
    List<Component> components = new ArrayList<>(footerComponents);
    if (taEntity.isNewEntity())
      components.removeIf(c -> UIUtils.DELETE_FORM.equals(c.getId().orElse("null")));
    footerLayout.setVisible(false);
    return components;
  }

  protected void init(TAView<E> taView) {
    this.taView = taView;
    this.taService = taView.taService;
  }

  @Override
  public boolean isValid() {
    return binder.validate().isOk();
  }

  @Override
  public E getEntity() {
    return binder.getBean();
  }

  @Override
  public void setEntity(E entity) {
    try {
      this.taEntity = entity;
      binder = new BeanValidationBinder<>(taView.taEntity);
      binder.bindInstanceFields(this);
      binder.setBean(entity);
      binder.addValueChangeListener(
          e -> {
            if (e.isFromClient()) {
              hasChanged = true;
            }
          });

    } catch (Exception e) {
      Log.error("", e);
    }
  }

  @Override
  public void beforeLeave(BeforeLeaveEvent event) {
    try {
      if (isValid() && confirmBeforeLeave && hasChanged) {
        ContinueNavigationAction action = event.postpone();
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader(getTranslation("ConfirmDialog.leaveEdit.title"));
        dialog.setText(getTranslation("ConfirmDialog.leaveEdit.message"));
        dialog.setCancelButton(
            getTranslation("ConfirmDialog.leaveEdit.leave"), e -> action.proceed());
        dialog.setConfirmButton(
            getTranslation("ConfirmDialog.leaveEdit.stay"), e -> dialog.close());
        dialog.setCancelable(true);
        dialog.open();
      }
    } catch (Exception e) {
      Log.error("", e);
    }
  }

  private void leaveWithoutConfirm() {
    confirmBeforeLeave = false;
    if (detailDialog != null) detailDialog.close();

    taView.sidebar.close();

    // VaadinContext context = VaadinService.getCurrent().getContext();
    // String route = RouteUtil.resolve(context, getClass());
    // UI.getCurrent().navigate(route);

    taView.grid.getDataProvider().refreshAll();

    // TODO: dont show the notification
    // UI.getCurrent().refreshCurrentRoute(false);
  }

  @Override
  public void onComponentEvent(ClickEvent<Button> event) {
    String cmpId = event.getSource().getId().orElse("null");
    confirmBeforeLeave = true;

    if (cmpId.startsWith(UIUtils.SAVE_FORM) && isValid()) {
      E entity = getEntity();
      Response response = taService.save(entity);
      if (UIUtils.showNotification(response)) {
        if (onSuccessTasks != null) {
          onSuccessTasks.forEach(UsersService::setTaskCompleted);
        }
        leaveWithoutConfirm();
      }
    }

    if (cmpId.startsWith(UIUtils.UPDATE_FORM) && isValid()) {
      E entity = getEntity();
      Response response = taService.save(entity);
      if (UIUtils.showNotification(response)) {
        hasChanged = false;
        if (onSuccessTasks != null) onSuccessTasks.forEach(UsersService::setTaskCompleted);
      }
    }

    if (cmpId.startsWith(UIUtils.CANCEL_FORM) || cmpId.startsWith(UIUtils.OK_FORM)) {
      leaveWithoutConfirm();
    }

    if (cmpId.startsWith(UIUtils.DELETE_FORM)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getClass().getSimpleName() + ".delete");
      if (!ok) {
        UIUtils.showErrorNotification(TAView.AUTH_ERROR_MSG);
        return;
      }
      // #GF-233 do nothing on delete when the entity is new
      E entity = getEntity();
      if (entity.isNewEntity()) return;
      ConfirmDialog dialog = UIUtils.getDeleteDialog(taEntity.getClass());
      dialog.addConfirmListener(
          e -> {
            Response response = taService.delete(entity.id);
            if (UIUtils.showNotification(response)) {
              leaveWithoutConfirm();
            }
          });
      dialog.open();
    }
  }
}
