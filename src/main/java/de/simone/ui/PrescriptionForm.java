package de.simone.ui;

import com.vaadin.flow.component.datetimepicker.*;
import com.vaadin.flow.component.select.*;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.*;

import de.simone.*;
import de.simone.backend.*;
import io.quarkus.panache.common.*;
import jakarta.annotation.security.*;
import jakarta.enterprise.inject.spi.*;

@RolesAllowed({ "Sitrone.master", "Prescription.edit" })
@Route(value = "Prescription", layout = MainLayout.class)
public class PrescriptionForm extends TAForm<Prescription> {

    private Select<Drug> drug;
    private TextField description;
    private TextField indications;
    private TextField contraindications;
    private TextField dosage;
    private IntegerField quantity;
    private DateTimePicker calendarWhen;
    private Select<String> calendarRepeat;
    private Select<String> calendarRemind;

    public PrescriptionForm() {
        drug = UIUtils.getDrugSelect("Prescription.drug", false, null);
        description = UIUtils.getTextField("Prescription.description");
        indications = UIUtils.getTextField("Prescription.indications");
        dosage = UIUtils.getTextField("Prescription.dosage");
        contraindications = UIUtils.getTextField("Prescription.contraindications");
        quantity = UIUtils.getIntegerField("Prescription.quantity");

        calendarWhen = UIUtils.getDateTimePicker("Prescription.calendarWhen");
        calendarRepeat = UIUtils.getSelect("calendar.repeat", "Prescription.calendarRepeat");
        calendarRemind = UIUtils.getSelect("calendar.remind", "Prescription.calendarRemind");

        addBodyComponets(
                drug,
                description,
                UIUtils.getHorizontalLayout2(quantity, dosage),
                indications,
                contraindications);
        addBodyComponets("prescription.separator1", calendarWhen, calendarRepeat, calendarRemind);
    }

    @Override
    public void setEntity(Prescription entity) {
        DrugsService drugsService = CDI.current().select(DrugsService.class).get();
        drug.setItems(drugsService.listAll(Sort.ascending("name")));
        super.setEntity(entity);
    }
}
