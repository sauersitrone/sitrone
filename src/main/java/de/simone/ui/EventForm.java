package de.simone.ui;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Event;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"Sitrone.master", "Event.edit"})
@Route(value = "Event", layout = MainLayout.class)
public class EventForm extends TAForm<Event> {

  private Select<String> type;
  private Select<String> status;
  private Select<String> reminderInterval;
  private TextField title;
  private TextArea content;
  private TextArea note;
  private IntegerField reminderCount;

  public EventForm() {
    type = UIUtils.getSelect("event.type", "Event.type");
    status = UIUtils.getSelect("event.status", "Event.status");
    reminderInterval = UIUtils.getSelect("event.remind", "Event.reminderInterval");
    title = UIUtils.getTextField("Event.title");
    content = UIUtils.getTextArea("Event.content");
    note = UIUtils.getTextArea("Event.note");
    reminderCount = UIUtils.getIntegerField("Event.reminderCount");
    addBodyComponets(type, title, status, content, reminderInterval, reminderCount, note);
  }

  @Override
  public void setEntity(Event entity) {

    boolean noEdit = !entity.isNewEntity();
    type.setReadOnly(noEdit);
    status.setReadOnly(noEdit);
    reminderInterval.setReadOnly(noEdit);
    title.setReadOnly(noEdit);
    title.setReadOnly(noEdit);
    content.setReadOnly(noEdit);
    reminderCount.setReadOnly(noEdit);
    
    super.setEntity(entity);
  }
}
