package de.simone.ui;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.TranslationProvider;
import de.simone.UIUtils;
import de.simone.backend.Event;
import de.simone.backend.EventsService;
import de.simone.backend.TAEntity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({"Sitrone.master", "Events"})
@Route(value = "Events", layout = MainLayout.class)
public class EventsView extends TAView<Event> {

  @Inject EventsService eventsService;

  public EventsView() {
    this.grid = UIUtils.getGrid(Event.class);

    // mobile
    grid.addColumn(
            new ComponentRenderer<>(
                ge -> {
                  return new Span();

                  // MovilListItem mli = new MovilListItem(ge.isEnabled, ge.firstName + " " +
                  // ge.lastName,
                  //         ge.email, null, null);
                  // mli.setAvatar(ge.firstName + " " + ge.lastName, ge.avatar);
                  // return mli;
                }))
        .setHeader(getTranslation("Event.content"))
        .setComparator(te -> te.content)
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(te -> te.title)
        .setComparator(te -> te.title)
        .setHeader(getTranslation("Event.title"))
        .setAutoWidth(true);

    grid.addColumn(ge -> TranslationProvider.getString("event.type", ge.type))
        .setHeader(getTranslation("Event.type"))
        .setComparator(te -> te.type)
        .setAutoWidth(true);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getEventStatusBadge))
        .setComparator(te -> te.status)
        .setHeader(getTranslation("Event.status"))
        .setAutoWidth(true);

        grid.addColumn(ge -> UIUtils.getFormatedLocalDateTime(ge.getCreatedAt()))
        .setComparator(TAEntity::getCreatedAt)
        .setHeader(getTranslation("TAEntity.createdAt"))
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);

  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    init(Event.class, EventForm.class, eventsService, true);

    SerializablePredicate<Event> filter =
        entity -> {
          String searchTerm = searchField.getValue().trim().toLowerCase();
          if (searchTerm.isEmpty()) return true;

          boolean m1 = entity.title.toLowerCase().contains(searchTerm);
          boolean m2 =
              TranslationProvider.getString("event.type", entity.type)
                  .toLowerCase()
                  .contains(searchTerm);
                  boolean m3 =
                  TranslationProvider.getString("event.status", entity.status)
                      .toLowerCase()
                      .contains(searchTerm);
    
          return m1 || m2 || m3;
        };

    setItems(eventsService.listAll(), filter);
  }
}
