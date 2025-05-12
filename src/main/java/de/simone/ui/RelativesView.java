package de.simone.ui;

import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.data.renderer.*;
import com.vaadin.flow.function.*;
import com.vaadin.flow.router.*;

import de.simone.*;
import de.simone.backend.*;
import jakarta.annotation.security.*;
import jakarta.inject.*;

@RolesAllowed({ "Sitrone.master", "Relatives" })
@Route(value = "Relatives", layout = MainLayout.class)
public class RelativesView extends TAView<Relative> {

    @Inject
    RelativesService relativesService;

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
    public void beforeEnter(BeforeEnterEvent event) {
        init(Relative.class, RelativeForm.class, relativesService);

        SerializablePredicate<Relative> filter = entity -> {
            String searchTerm = searchField.getValue().trim().toLowerCase().toLowerCase();
            if (searchTerm.isEmpty())
                return true;

            boolean m1 = entity.firstName.toLowerCase().contains(searchTerm);
            boolean m2 = entity.lastName.toString().toLowerCase().contains(searchTerm);

            return m1 || m2;
        };
        setItems(relativesService.listAll(), filter);
    }
}
