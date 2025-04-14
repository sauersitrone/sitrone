package de.simone.ui;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.HistoriesService;
import de.simone.backend.History;
import io.quarkus.panache.common.Parameters;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({"Zitrone.master", "Histories"})
@Route(value = "Histories", layout = MainLayout.class)
public class HistoriesView extends TAView<History> implements HasUrlParameter<Long> {

  public static final String HISTORY_ACTION = "action.history";
  protected Long adultId;

  @Inject HistoriesService historiesService;

  public HistoriesView() {
    this.grid = UIUtils.getGrid(History.class);

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
  public void setParameter(BeforeEvent event, @OptionalParameter Long adultId) {
    this.adultId = adultId;
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
        historiesService.list("adultId = :adultId", Parameters.with("adultId", adultId)), filter);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    // inthis class, the logic is on setParameter(BeforeEvent event, @OptionalParameter Long
    // adultId)  executed
  }
}
