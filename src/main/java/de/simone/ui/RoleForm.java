package de.simone.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.backend.EndPoint;
import de.simone.backend.EndPointsService;
import de.simone.backend.Role;
import de.simone.backend.User;
import de.simone.backend.UsersService;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({ "Sitrone.master", "Role.edit" })
@Route(value = "Role")
public class RoleForm extends TAForm<Role> {

    private TextField roleName;
    private TextField description;
    private Select<String> selectionMethod;
    private MultiSelectListBox<String> endPoints;
    private Grid<User> usersInRole;
    private Checkbox isTemplate;

    public RoleForm() {
        this.usersInRole = new Grid<>(User.class, false);
        usersInRole.setId("usersInRole");
        usersInRole.addColumn("userName").setHeader(getTranslation("User.userName"))
                .setComparator(te -> te.userName).setSortable(true).setAutoWidth(true);
        usersInRole.addColumn("firstName").setHeader(getTranslation("User.firstName"))
                .setComparator(te -> te.firstName).setSortable(true).setAutoWidth(true);
        usersInRole.addColumn("lastName").setHeader(getTranslation("User.lastName"))
                .setComparator(te -> te.lastName).setSortable(true).setAutoWidth(true);

        roleName = UIUtils.getTextField("Role.roleName", true, false);
        description = UIUtils.getTextField("Role.description");
        selectionMethod = UIUtils.getSelect("selectionMethods", "Role.selectionMethod");
        endPoints = UIUtils.getMultiSelectListBox("Role.endPoints");
        endPoints.setHeight("300px");
        VerticalLayout endPointWraper = UIUtils.getWrapForMultiSelectListBox(endPoints);
        endPointWraper.setHeightFull();
        isTemplate = UIUtils.getCheckbox("Role.isTemplate");
        // VerticalLayout isTemplateWrap = UIUtils.getWrapForCheckBox(isTemplate);

        VerticalLayout gridWraper = UIUtils.getWrapForGrid(usersInRole);
        gridWraper.setHeightFull();

        addBodyComponets("separator.general", roleName, description, selectionMethod);
        addBodyComponets("Role.endPoints", true, endPoints);

    }

    @Override
    public void setEntity(Role entity) {
            // prescriptionsService = CDI.current().select(PrescriptionsService.class).get();
        UsersService usersService = new UsersService();
        EndPointsService endPointsService = new EndPointsService();
        usersInRole.setItems(usersService.list("role_id = :role_id", Parameters.with("role_id", entity.id)));
        List<EndPoint> list = endPointsService.listAll(Sort.ascending("target"));
        List<String> values = new ArrayList<>();
        list.forEach(ep -> values.add(ep.target));
        endPoints.setItems(values);

        // add separator for views
        values.forEach(v -> {
            if (!v.contains("."))
                endPoints.addComponents(v, new Hr());
        });

        // no edit for spetial roles
        roleName.setReadOnly(entity.isSpetialRole());

        // sitrone panel
        if (SecurityUtils.getLoggedUser().isAdmin())
        addBodyComponets("separator.sitrone", isTemplate);

        super.setEntity(entity);
    }
}
