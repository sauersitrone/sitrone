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
import de.simone.backend.Prescription;
import de.simone.backend.PrescriptionsService;
import io.quarkus.panache.common.Parameters;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({ "Sitrone.master", "Prescriptions" })
@Route(value = "Prescriptions", layout = MainLayout.class)
public class PrescriptionsView extends TAView<Prescription> implements HasUrlParameter<Long> {

  public static final String Prescription_ACTION = "action.Prescription";
  protected Long adultId;

  @Inject
  PrescriptionsService prescriptionsService;

  public PrescriptionsView() {
    this.grid = UIUtils.getGrid(Prescription.class);

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
        .setHeader(getTranslation("Prescription.fullName"))
        .setComparator(te -> te.dosage)
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(Prescription::getCronDescription)
        .setHeader(getTranslation("Prescription.calendarWhen"))
        .setComparator(Prescription::getCronDescription)
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.dosage)
        .setHeader(getTranslation("Prescription.dosage"))
        .setComparator(te -> te.dosage)
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.quantity)
        .setHeader(getTranslation("Prescription.quantity"))
        .setComparator(te -> te.quantity)
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);

    grid.addColumn(te -> te.indications)
        .setHeader(getTranslation("Prescription.indications"))
        .setComparator(te -> te.indications)
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.contraindications)
        .setHeader(getTranslation("Prescription.contraindications"))
        .setComparator(te -> te.contraindications)
        .setSortable(true)
        .setAutoWidth(true);
  }

  @Override
  public void setParameter(BeforeEvent event, @OptionalParameter Long adultId) {
    this.adultId = adultId;
    init(Prescription.class, PrescriptionForm.class, prescriptionsService);

    SerializablePredicate<Prescription> filter = entity -> {
      String searchTerm = searchField.getValue().trim().toLowerCase().toLowerCase();
      if (searchTerm.isEmpty())
        return true;

      boolean m1 = entity.dosage.toLowerCase().contains(searchTerm);
      boolean m2 = entity.quantity.toString().toLowerCase().contains(searchTerm);
      boolean m3 = entity.indications.toLowerCase().contains(searchTerm);
      boolean m4 = entity.contraindications.toLowerCase().contains(searchTerm);

      return m1 || m2 || m3 || m4;
    };
    setItems(
        prescriptionsService.list("adultId = :adultId", Parameters.with("adultId", adultId)),
        filter);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    //
  }
}
