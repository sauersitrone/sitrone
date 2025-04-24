package de.simone.ui;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Drug;
import de.simone.backend.DrugsService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({"Sitrone.master", "Drugs"})
@Route(value = "Drugs", layout = MainLayout.class)
public class DrugsView extends TAView<Drug> {

  @Inject DrugsService medicinesService;

  public DrugsView() {
    this.grid = UIUtils.getGrid(Drug.class);

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
        .setHeader(getTranslation("Drug.name"))
        .setComparator(te -> te.name)
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getDrugRender))
        .setHeader(getTranslation("Drug.name"))
        .setComparator(te -> te.name)
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> te.shape)
        .setComparator(te -> te.shape)
        .setHeader(getTranslation("Drug.shape"))
        .setAutoWidth(true);

    grid.addColumn(te -> te.strength)
        .setHeader(getTranslation("Drug.strength"))
        .setComparator(te -> te.strength)
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);

    grid.addColumn(te -> te.supplier)
        .setComparator(te -> te.supplier)
        .setHeader(getTranslation("Drug.supplier"))
        .setAutoWidth(true);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    init(Drug.class, DrugForm.class, medicinesService, true);

    SerializablePredicate<Drug> filter =
        entity -> {
          String searchTerm = searchField.getValue().trim().toLowerCase();
          if (searchTerm.isEmpty()) return true;

          boolean m1 = entity.name.toLowerCase().contains(searchTerm);
          boolean m2 = entity.shape.toLowerCase().contains(searchTerm);
          boolean m3 = entity.strength.toString().contains(searchTerm);
          boolean m4 = entity.supplier.toLowerCase().contains(searchTerm);

          return m1 || m2 || m3 || m4;
        };

    setItems(medicinesService.listAll(), filter);
  }
}
