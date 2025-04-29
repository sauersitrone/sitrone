package de.simone.ui;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.*;
import io.quarkus.panache.common.Parameters;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({ "Sitrone.master", "family" })
@Route(value = "family", layout = MainLayout.class)
public class FamilyView extends TAView<Family> implements HasUrlParameter<Long> {

  public static final String Prescription_ACTION = "action.Prescription";
  protected Long adultId;

  @Inject
  FamiliesService familiesService;

  public FamilyView() {
    this.grid = UIUtils.getGrid(Family.class);

    // mobile
    grid.addColumn(
        new ComponentRenderer<>(
            ge -> {
              return new Span();

              // MovilListItem mli = new MovilListItem(ge.isEnabled, ge.firstName + " " +
              // ge.lastName,
              // ge.email, null, null);
              // mli.setAvatar(ge.firstName + " " + ge.lastName, ge.avatar);
              // return mli;
            }))
        .setHeader(getTranslation("Address.fullName"))
        .setComparator(te -> Address.getFullName(te.firstName, te.lastName))
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getAdutRender))
        .setHeader(getTranslation("Address.fullName"))
        .setComparator(te -> Address.getFullName(te.firstName, te.lastName))
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.email)
        .setComparator(te -> te.email)
        .setHeader(getTranslation("Address.email"))
        .setAutoWidth(true);

    grid.addColumn(te -> te.phone)
        .setComparator(te -> te.phone)
        .setHeader(getTranslation("Address.phone"))
        .setAutoWidth(true);

    grid.addColumn(te -> UIUtils.getFormatedLocalDate(te.birdthDate))
        .setHeader(getTranslation("Address.birdthDate"))
        .setComparator(te -> te.birdthDate)
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);
  }

  @Override
  public void setParameter(BeforeEvent event, @OptionalParameter Long adultId) {
    this.adultId = adultId;
    init(Family.class, PrescriptionForm.class, familiesService);

    SerializablePredicate<Family> filter = entity -> {
      String searchTerm = searchField.getValue().trim().toLowerCase().toLowerCase();
      if (searchTerm.isEmpty())
        return true;

      boolean m1 = entity.firstName.toLowerCase().contains(searchTerm);
      boolean m2 = entity.lastName.toString().toLowerCase().contains(searchTerm);
      boolean m3 = entity.email.toLowerCase().contains(searchTerm);
      boolean m4 = entity.phone.toLowerCase().contains(searchTerm);

      return m1 || m2 || m3 || m4;
    };
    setItems(familiesService.list("adultId = :adultId", Parameters.with("adultId", adultId)), filter);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    // inthis class, the logic is on setParameter(BeforeEvent event,
    // @OptionalParameter Long
    // adultId) executed
  }
}
