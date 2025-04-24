package de.simone.ui;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Drug;
import de.simone.backend.DrugsService;
import de.simone.backend.Prescription;
import de.simone.backend.PrescriptionsService;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.inject.spi.CDI;

@RolesAllowed({"Sitrone.master", "Prescription.edit"})
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
    DrugsService   drugsService = CDI.current().select(DrugsService.class).get();
    drug.setItems(drugsService.listAll(Sort.ascending("name")));
    entity.adultId = ((PrescriptionsView) taView).adultId;
    super.setEntity(entity);
  }
}
