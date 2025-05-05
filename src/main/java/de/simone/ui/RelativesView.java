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

import de.simone.*;
import de.simone.backend.*;
import io.quarkus.panache.common.Parameters;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({ "Sitrone.master", "Relatives" })
@Route(value = "Relatives", layout = MainLayout.class)
public class RelativesView extends TAView<Relative> implements HasUrlParameter<Long> {

  protected Long adultId;

  @Inject
  RelativesService familiesService;

  public RelativesView() {
    this.grid = UIUtils.getGrid(Relative.class);

    // mobile
    grid.addColumn(
        new ComponentRenderer<>(
            te -> {
              return new Span();

              // MovilListItem mli = new MovilListItem(ge.isEnabled, ge.firstName + " " +
              // ge.lastName,
              // ge.email, null, null);
              // mli.setAvatar(ge.firstName + " " + ge.lastName, ge.avatar);
              // return mli;
            }))
        .setHeader(getTranslation("Person.fullName"))
        .setComparator(te -> Address.getFullName(te.firstName, te.lastName))
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getRelativeRender))
        .setHeader(getTranslation("Person.fullName"))
        .setComparator(te -> Address.getFullName(te.firstName, te.lastName))
        .setSortable(true)
        .setAutoWidth(true);

    grid.addColumn(te -> TranslationProvider.getString("relative.relation", te.relation))
        .setHeader(getTranslation("Person.relation"))
        .setComparator(te -> te.relation)
        .setAutoWidth(true);

    grid.addColumn(te -> UIUtils.getFormatedLocalDate(te.birdthDate))
        .setHeader(getTranslation("Person.birdthDate"))
        .setComparator(te -> te.birdthDate)
        .setTextAlign(ColumnTextAlign.END)
        .setAutoWidth(true);
  }

  @Override
  public void setParameter(BeforeEvent event, @OptionalParameter Long adultId) {
    this.adultId = adultId;
    init(Relative.class, RelativeForm.class, familiesService);

    SerializablePredicate<Relative> filter = entity -> {
      String searchTerm = searchField.getValue().trim().toLowerCase().toLowerCase();
      if (searchTerm.isEmpty())
        return true;

      boolean m1 = entity.firstName.toLowerCase().contains(searchTerm);
      boolean m2 = entity.lastName.toString().toLowerCase().contains(searchTerm);

      return m1 || m2;
    };
    setItems(familiesService.list("adultId = :adultId", Parameters.with("adultId", adultId)), filter);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    //
  }
}
