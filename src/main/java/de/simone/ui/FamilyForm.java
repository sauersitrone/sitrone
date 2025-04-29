package de.simone.ui;

import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.datepicker.*;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.*;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.*;
import de.simone.ui.components.FileLoaderSimple;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({ "Sitrone.master", "Family.edit" })
@Route(value = "Adult", layout = MainLayout.class)
public class FamilyForm extends TAForm<Family> {

  private TextField firstName;
  private TextField lastName;
  private TextField phone;
  private EmailField email;
  private Select<String> relation;
  private Select<String> gender;
  private Select<String> maritalStatus;
  private MultiSelectComboBox<String> interests;
  private FileLoaderSimple avatar;
  private DatePicker  birdthDate;

  public FamilyForm() {
    avatar = new FileLoaderSimple("Adult.avatar");
    firstName = UIUtils.getTextField("Adult.firstName");
    lastName = UIUtils.getTextField("Adult.lastName");
    email = UIUtils.getEmailField("Adult.email");
    phone = UIUtils.getTextField("Adult.phone");
    phone.setRequiredIndicatorVisible(false);
    preferredLanguage = UIUtils.getSelect("languages", "Adult.preferredLanguage");
    relationship = UIUtils.getSelect("adult.relationship", "Adult.relationship");
    gender = UIUtils.getSelect("adult.gender", "Adult.gender");
    maritalStatus = UIUtils.getSelect("adult.maritalStatus", "Adult.maritalStatus");
    birdthDate = UIUtils.getDatePicker("Address.birdthDate");
    interests = UIUtils.getMultiSelectComboBox("adult.interests", "Adult.interests", false);

    addBodyComponets(
        UIUtils.getHorizontalLayout2(firstName, lastName),
        UIUtils.getHorizontalLayout2(email, phone),
        UIUtils.getHorizontalLayout2(gender, maritalStatus),
        relationship,
        preferredLanguage,
        birdthDate, interests,
        avatar);
  }
}
