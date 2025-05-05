package de.simone.ui;

import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.datepicker.*;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.selection.*;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.*;
import de.simone.ui.components.FileLoaderSimple;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({ "Sitrone.master", "Relative.edit" })
@Route(value = "Relative", layout = MainLayout.class)
public class RelativeForm extends TAForm<Relative> {

  private TextField firstName;
  private TextField lastName;
  private Select<String> relation;
  private FileLoaderSimple foto;
  private DatePicker birdthDate;
  private TextArea details;

  public RelativeForm() {
    foto = new FileLoaderSimple("Person.foto");
    firstName = UIUtils.getTextField("Person.firstName");
    lastName = UIUtils.getTextField("Person.lastName");
    relation = UIUtils.getSelect("relative.relation", "Person.relation");
    birdthDate = UIUtils.getDatePicker("Person.birdthDate");
    details = UIUtils.getTextArea("Person.details", true, false);

    addBodyComponets(UIUtils.getHorizontalLayout2(firstName, lastName),
        UIUtils.getHorizontalLayout2(relation, birdthDate), foto, details);
  }
}
