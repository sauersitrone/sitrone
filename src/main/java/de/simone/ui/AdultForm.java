package de.simone.ui;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Adult;
import de.simone.ui.components.FileLoaderSimple;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"Zitrone.master", "Adult.edit"})
@Route(value = "Adult", layout = MainLayout.class)
public class AdultForm extends TAForm<Adult> {

  private TextField firstName;
  private TextField lastName;
  private TextField phone;
  private EmailField email;
  private Select<String> preferredLanguage;
  private Select<String> relationship;
  private FileLoaderSimple avatar;
  private IntegerField birdthYear;

  public AdultForm() {
    avatar = new FileLoaderSimple("Adult.avatar");
    firstName = UIUtils.getTextField("Adult.firstName");
    lastName = UIUtils.getTextField("Adult.lastName");
    email = UIUtils.getEmailField("Adult.email");
    phone = UIUtils.getTextField("Adult.phone");
    phone.setRequiredIndicatorVisible(false);
    preferredLanguage = UIUtils.getSelect("languages", "Adult.preferredLanguage");
    relationship = UIUtils.getSelect("adult.relationship", "Adult.relationship");
    birdthYear = UIUtils.getIntegerField("Adult.birdthYear");
    addBodyComponets(
        UIUtils.getHorizontalLayout2(firstName, lastName),
        UIUtils.getHorizontalLayout2(email, phone),
        relationship,
        preferredLanguage,
        birdthYear,
        avatar);
  }
}
