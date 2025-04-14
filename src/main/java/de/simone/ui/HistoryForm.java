package de.simone.ui;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.History;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"Zitrone.master", "History.edit"})
@Route(value = "History", layout = MainLayout.class)
public class HistoryForm extends TAForm<History> {

  private Select<String> mood;
  private TextArea note;
  private IntegerField height;
  private IntegerField weight;

  public HistoryForm() {
    mood = UIUtils.getSelect("history.mood", "History.mood");
    note = UIUtils.getTextArea("History.note");
    height = UIUtils.getIntegerField("History.height");
    weight = UIUtils.getIntegerField("History.weight");
    addBodyComponets(mood, UIUtils.getHorizontalLayout2(height, weight), note);
  }

  @Override
  public void setEntity(History entity) {
    if (entity.isNewEntity()) entity.type = History.MANUAL;

    boolean editable = History.MANUAL.equals(entity.type) || entity.id  == null;
    mood.setReadOnly(!editable);
    height.setReadOnly(!editable);
    weight.setReadOnly(!editable);
    entity.adultId = ((HistoriesView) taView).adultId;
    super.setEntity(entity);
  }
}
