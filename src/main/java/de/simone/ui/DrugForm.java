package de.simone.ui;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Drug;
import de.simone.backend.DrugsService;
import de.simone.ui.components.FileLoaderSimple;
import de.simone.ui.components.TColorPicker;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({"Zitrone.master", "Drug.edit"})
@Route(value = "Drug", layout = MainLayout.class)
public class DrugForm extends TAForm<Drug> {

  private Select<String> shape;
  private TextField imprint;
  private TextField supplier;
  private TextField ndc9;
  private TextField name;
  private TColorPicker primaryColor;
  private TColorPicker secundaryColor;
  private FileLoaderSimple imageMedicament;
  private FileLoaderSimple imageBox;
  private IntegerField strength;
  private IntegerField refillFrom;

  @Inject protected DrugsService medicinesService;

  public DrugForm() {
    shape = UIUtils.getSelect("drug.shape", "Drug.shape");
    imprint = UIUtils.getTextField("Drug.imprint");
    name = UIUtils.getTextField("Drug.name");
    primaryColor = UIUtils.getTColorPicker("Drug.primaryColor", false);
    secundaryColor = UIUtils.getTColorPicker("Drug.secundaryColor", false);
    supplier = UIUtils.getTextField("Drug.supplier");
    ndc9 = UIUtils.getTextField("Drug.ndc9");
    imageMedicament = new FileLoaderSimple("Drug.imageMedicament");
    imageBox = new FileLoaderSimple("Drug.imageBox");
    strength = UIUtils.getIntegerField("Drug.strength");
    refillFrom = UIUtils.getIntegerField("Drug.refillFrom");
    addBodyComponets(
        name,
        shape, primaryColor, secundaryColor,
        imprint, UIUtils.getHorizontalLayout2(strength, refillFrom),supplier, ndc9,
        imageMedicament,
        imageBox);
  }

}
