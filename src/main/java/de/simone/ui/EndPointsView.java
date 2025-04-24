package de.simone.ui;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.EndPoint;
import de.simone.backend.EndPointsService;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({ "Sitrone.master" })
@Route(value = "EndPoints", layout = MainLayout.class)
public class EndPointsView extends TAView<EndPoint> {

    @Inject
    EndPointsService endPointsService;

    public EndPointsView() {
        this.grid = UIUtils.getGrid(EndPoint.class);

        // mobile
        grid.addColumn(new ComponentRenderer<>(ge -> {
            return new Span();
            // MovilListItem mli = new MovilListItem(null, ge.target, ge.description, null, null);
            // return mli;
        })).setHeader(getTranslation("EndPoint.target")).setComparator(te -> te.target)
                .setSortable(true);

        // desktop
        UIUtils.setIdColumn(grid);

        grid.addColumn("target").setHeader(getTranslation("EndPoint.target"))
                .setComparator(te -> te.target).setSortable(true).setAutoWidth(true);

        grid.addColumn("description").setHeader(getTranslation("EndPoint.description"))
                .setComparator(te -> te.description).setSortable(true).setAutoWidth(true);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        init(EndPoint.class, EndPointForm.class, endPointsService);
        SerializablePredicate<EndPoint> filter = entity -> {
            String searchTerm = searchField.getValue().trim().toLowerCase();
            if (searchTerm.isEmpty())
                return true;

            boolean m1 = entity.target.toLowerCase().contains(searchTerm);
            boolean m2 = entity.description.toLowerCase().contains(searchTerm);
            return m1 || m2;
        };
        setItems(endPointsService.listAll(Sort.ascending("target")), filter);
    }

}
