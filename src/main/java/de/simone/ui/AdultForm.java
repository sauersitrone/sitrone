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
import de.simone.backend.Adult;
import de.simone.ui.components.FileLoaderSimple;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({ "Sitrone.master", "Person.edit" })
@Route(value = "Adult", layout = MainLayout.class)
public class AdultForm extends TAForm<Adult> {

  private TextField firstName;
  private TextField lastName;
  private TextField phone;
  private EmailField email;
  private Select<String> preferredLanguage;
  private Select<String> relationship;
  private Select<String> gender;
  private Select<String> maritalStatus;
  private MultiSelectComboBox<String> interests;
  private FileLoaderSimple avatar;
  private DatePicker  birdthDate;

  public AdultForm() {
    avatar = new FileLoaderSimple("Person.avatar");
    firstName = UIUtils.getTextField("Person.firstName");
    lastName = UIUtils.getTextField("Person.lastName");
    email = UIUtils.getEmailField("Person.email");
    phone = UIUtils.getTextField("Person.phone");
    phone.setRequiredIndicatorVisible(false);
    preferredLanguage = UIUtils.getSelect("languages", "Person.preferredLanguage");
    relationship = UIUtils.getSelect("adult.relationship", "Person.relationship");
    gender = UIUtils.getSelect("person.gender", "Person.gender");
    maritalStatus = UIUtils.getSelect("person.maritalStatus", "Person.maritalStatus");
    birdthDate = UIUtils.getDatePicker("Person.birdthDate");
    interests = UIUtils.getMultiSelectComboBox("person.interests", "Person.interests", false);

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
