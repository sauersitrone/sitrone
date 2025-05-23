package de.simone.ui;

import com.vaadin.flow.component.grid.Grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.data.renderer.*;
import com.vaadin.flow.function.*;
import com.vaadin.flow.router.*;

import de.simone.*;
import de.simone.backend.*;
import jakarta.annotation.security.*;
import jakarta.inject.*;

@RolesAllowed({ "Sitrone.master", "Roles" })
@Route(value = "Roles", layout = MainLayout.class)
public class RolesView extends TAView<Role> {

    @Inject
    RolesService rolesService;

    private Column<Role> isTemplate;
    private static final String SELECTION_METHODS = "selectionMethods";

    public RolesView() {
        this.grid = UIUtils.getGrid(Role.class);

        // mobile
        grid.addColumn(new ComponentRenderer<>(ge -> {
            return new Span();
            // MovilListItem mli = new MovilListItem(null, ge.roleName, ge.description,
            // null, null);
            // return mli;
        })).setHeader(getTranslation("Role.roleName")).setComparator(te -> te.roleName)
                .setSortable(true);

        // desktop
        UIUtils.setIdColumn(grid);

        grid.addColumn("roleName").setHeader(getTranslation("Role.roleName"))
                .setComparator(te -> te.roleName).setSortable(true).setAutoWidth(true);

        isTemplate = grid.addColumn(new ComponentRenderer<>(ge -> UIUtils.getOnlyTrueBooleanBadge(ge.isTemplate)))
                .setHeader(getTranslation("Role.isTemplate")).setSortable(true)
                .setAutoWidth(true);

        grid.addColumn("description").setHeader(getTranslation("Role.description"))
                .setComparator(te -> te.description).setSortable(true).setAutoWidth(true);

        grid.addColumn(ge -> TranslationProvider.getString(SELECTION_METHODS, ge.selectionMethod))
                .setHeader(getTranslation("Role.selectionMethod"))
                .setComparator(ge -> TranslationProvider.getString(SELECTION_METHODS, ge.selectionMethod))
                .setSortable(true).setAutoWidth(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        init(Role.class, RoleForm.class, rolesService);
        removeToolBarComponents(UIUtils.DUPLICATE_ACTION);
        addOneTimeTip(HanniTask.TIP_ROLLE);

        // only visible for account gooddev
        isTemplate.getElement().setProperty(UIUtils.COLUMN_VISIBLE, SecurityUtils.getLoggedUser().isAdmin());

        SerializablePredicate<Role> filter = entity -> {
            String searchTerm = searchField.getValue().trim().toLowerCase();
            if (searchTerm.isEmpty())
                return true;

            boolean m1 = entity.roleName.toLowerCase().contains(searchTerm);
            boolean m2 = entity.description.toLowerCase().contains(searchTerm);
            boolean m3 = TranslationProvider.getString(SELECTION_METHODS, entity.selectionMethod).toLowerCase()
                    .contains(searchTerm);
            return m1 || m2 || m3;
        };
        setItems(rolesService.listAll(), filter);
    }
}
