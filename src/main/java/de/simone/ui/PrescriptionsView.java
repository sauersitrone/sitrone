package de.simone.ui;

import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.data.renderer.*;
import com.vaadin.flow.function.*;
import com.vaadin.flow.router.*;

import de.simone.*;
import de.simone.backend.*;
import jakarta.annotation.security.*;
import jakarta.inject.*;

@RolesAllowed({ "Sitrone.master", "Prescriptions" })
@Route(value = "Prescriptions", layout = MainLayout.class)
public class PrescriptionsView extends TAView<Prescription> {

    public static final String Prescription_ACTION = "action.Prescription";

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
    public void beforeEnter(BeforeEnterEvent event) {
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
                prescriptionsService.listAll(), filter);
    }
}
