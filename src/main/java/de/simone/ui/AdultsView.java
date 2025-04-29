package de.simone.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;

import de.simone.MainLayout;
import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.backend.Adult;
import de.simone.backend.AdultsService;
import de.simone.backend.MessagingProvidersService;
import de.simone.ui.components.MaterialIcons;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@RolesAllowed({"Sitrone.master", "Adults"})
@Route(value = "Adults", layout = MainLayout.class)
public class AdultsView extends TAView<Adult> {

  @Inject AdultsService adultsService;
  @Inject MessagingProvidersService messagingProvidersService;

  public static final String ADULT_HISTORY = "action.adultHistory";
  public static final String ADULT_PRESCRIPTIONS = "action.adultPrescriptions";
  public static final String ADULT_FAMILY = "action.adultFamily";
  public static final String ADULT_TEST_MESSAGING = "action.adultTestMessaging";

  public AdultsView() {
    this.grid = UIUtils.getGrid(Adult.class);

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
        .setHeader(getTranslation("Adult.fullName"))
        .setComparator(te -> te.firstName + " " + te.lastName)
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);


    // TODO: complement implementatio of element actions
    // grid.addColumn(
    //         new ComponentRenderer<>(
    //             ge -> {
    //               AditionalAction croop =
    //                   new AditionalAction(
    //                       IMAGE_CROPP,
    //                       "materialicons",
    //                       VaadinIcon.ABACUS.getName(VaadinIcon.ABACUS));
    //               croop.isEnabled = (ge instanceof MediaImage);
    //               RecordActions menu = UIUtils.getRecordActions(ge.getId(), this, croop);
    //               return menu.menuBar;
    //             }))
    //     .setWidth("80px")
    //     .setFlexGrow(0);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getAdutRender))
        .setHeader(getTranslation("Adult.fullName"))
        .setComparator(te -> te.firstName + " " + te.lastName)
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.email)
        .setComparator(te -> te.email)
        .setHeader(getTranslation("Adult.email"))
        .setAutoWidth(true);

    grid.addColumn(te -> te.phone)
        .setComparator(te -> te.phone)
        .setHeader(getTranslation("Adult.phone"))
        .setAutoWidth(true);

    grid.addColumn(te -> UIUtils.getFormatedLocalDate(te.birdthDate))
        .setHeader(getTranslation("Address.birdthDate"))
        .setComparator(te -> te.birdthDate)
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    init(Adult.class, AdultForm.class, adultsService, false);
    Icon history =
        UIUtils.getAfterIcon(MaterialIcons.EVENT_NOTE.create(), ADULT_HISTORY, UIUtils.EDIT_ACTION);
    Icon recepts =
        UIUtils.getAfterIcon(MaterialIcons.MEDICATION.create(), ADULT_PRESCRIPTIONS, UIUtils.EDIT_ACTION);
    Icon family =
        UIUtils.getAfterIcon(MaterialIcons.FAMILY_RESTROOM.create(), ADULT_FAMILY, UIUtils.EDIT_ACTION);

    // Icon testMessaging =
    //     UIUtils.getAfterIcon(
    //         FontAwesome.Brands.WHATSAPP.create(), ADULT_TEST_MESSAGING, UIUtils.EDIT_ACTION);

    Icon testMessaging =
        UIUtils.getAfterIcon(
            VaadinIcon.PAPERPLANE.create(), ADULT_TEST_MESSAGING, UIUtils.EDIT_ACTION);

    setToolBarComponents(UIUtils.getToolBar(this, history, recepts, family, testMessaging));
    removeToolBarComponents(UIUtils.DUPLICATE_ACTION);
    SerializablePredicate<Adult> filter =
        entity -> {
          String searchTerm = searchField.getValue().trim().toLowerCase();
          if (searchTerm.isEmpty()) return true;

          boolean m1 = entity.firstName.toLowerCase().contains(searchTerm);
          boolean m2 = entity.lastName.toLowerCase().contains(searchTerm);
          boolean m3 = entity.email.toLowerCase().contains(searchTerm);
          boolean m4 = entity.phone.toLowerCase().contains(searchTerm);

          return m1 || m2 || m3 || m4;
        };

    setItems(adultsService.listAll(), filter);
  }

  @Override
  public void onComponentEvent(ClickEvent<Button> event) {
    Button button = event.getSource();
    String cmpId = button.getId().orElse("null");

    if (cmpId.startsWith(ADULT_HISTORY)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".adult.history");
      if (!ok) {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
        return;
      }

      Adult entity = getSelectedItem();
      if (entity == null) return;

      String url = this.viewUrl = RouteUtil.resolve(context, HistoriesView.class);
      UI.getCurrent().navigate(url + "/" + entity.id);
      return;
    }

    if (cmpId.startsWith(ADULT_PRESCRIPTIONS)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".adult.prescription");
      if (!ok) {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
        return;
      }

      Adult entity = getSelectedItem();
      if (entity == null) return;

      String url = this.viewUrl = RouteUtil.resolve(context, PrescriptionsView.class);
      UI.getCurrent().navigate(url + "/" + entity.id);
      return;
    }

    if (cmpId.startsWith(ADULT_PRESCRIPTIONS)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".adult.family");
      if (!ok) {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
        return;
      }

      Adult entity = getSelectedItem();
      if (entity == null) return;

      String url = this.viewUrl = RouteUtil.resolve(context, PrescriptionsView.class);
      UI.getCurrent().navigate(url + "/" + entity.id);
      return;
    }

    if (cmpId.startsWith(ADULT_TEST_MESSAGING)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".adult.testMessaging");
      if (!ok) {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
        return;
      }

      Adult entity = getSelectedItem();
      if (entity == null) return;

      Response response = messagingProvidersService.sendWhatsappMessage(entity, "hello_world");
      UIUtils.showNotification(response);
      return;
    }

    super.onComponentEvent(event);
  }
}
