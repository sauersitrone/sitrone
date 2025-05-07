package de.simone.ui;

import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.datepicker.*;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.*;
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
  private FileLoaderSimple picture;
  private DatePicker  birdthDate;
  private MultiSelectComboBox<String> personality;
  private MultiSelectComboBox<String> interests;
  private MultiSelectComboBox<String> ocupation;
  
  public AdultForm() {
    picture = new FileLoaderSimple("Person.picture");
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
    personality = UIUtils.getMultiSelectComboBox("person.personality", "Person.personality", false);
    interests = UIUtils.getMultiSelectComboBox("person.interests", "Person.interests", false);
    ocupation = UIUtils.getMultiSelectComboBox("person.ocupation", "Person.ocupation", false);

    addBodyComponets(
        UIUtils.getHorizontalLayout2(firstName, lastName),
        UIUtils.getHorizontalLayout2(email, phone),
        UIUtils.getHorizontalLayout2(gender, maritalStatus),
        relationship,
        preferredLanguage,
        birdthDate, 
        picture);

        addBodyComponets("personality.separator", false, personality, interests, ocupation);

  }
}
