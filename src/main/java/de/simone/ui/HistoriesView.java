package de.simone.ui;

import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.data.renderer.*;
import com.vaadin.flow.function.*;
import com.vaadin.flow.router.*;

import de.simone.*;
import de.simone.backend.*;
import io.quarkus.panache.common.*;
import jakarta.annotation.security.*;
import jakarta.inject.*;

@RolesAllowed({"Sitrone.master", "Histories"})
@Route(value = "Histories", layout = MainLayout.class)
public class HistoriesView extends TAView<History> {

  public static final String HISTORY_ACTION = "action.history";

  @Inject HistoriesService historiesService;

  public HistoriesView() {
    this.grid = UIUtils.getGrid(History.class);

    // mobile
    grid.addColumn(
            new ComponentRenderer<>(
                te -> {
                  return new Span();

                  // MovilListItem mli = new MovilListItem(ge.isEnabled, ge.firstName + " " +
                  // ge.lastName,
                  //         ge.email, null, null);
                  // mli.setAvatar(ge.firstName + " " + ge.lastName, ge.avatar);
                  // return mli;
                }))
        .setHeader(getTranslation("History.fullName"))
        .setComparator(te -> te.weight)
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getHistoryRender))
        .setHeader(getTranslation("History.mood"))
        .setComparator(te -> te.mood)
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.weight)
        .setHeader(getTranslation("History.weight"))
        .setComparator(te -> te.weight)
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);

    grid.addColumn(te -> te.height)
        .setHeader(getTranslation("History.height"))
        .setComparator(te -> te.height)
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    init(History.class, HistoryForm.class, historiesService, false);
    setToolBarComponents(UIUtils.getToolBar(this));
    removeToolBarComponents(UIUtils.DUPLICATE_ACTION);

    SerializablePredicate<History> filter =
        entity -> {
          String searchTerm = searchField.getValue().trim().toLowerCase();
          if (searchTerm.isEmpty()) return true;

          boolean m1 = entity.mood.toLowerCase().contains(searchTerm);
          boolean m2 = entity.weight.toString().toLowerCase().contains(searchTerm);
          boolean m3 = entity.height.toString().toLowerCase().contains(searchTerm);

          return m1 || m2 || m3;
        };

    setItems(
        historiesService.listAll(), filter);
  }

}
