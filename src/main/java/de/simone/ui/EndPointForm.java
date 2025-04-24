package de.simone.ui;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.EndPoint;
import de.simone.backend.EndPointsService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({"Sitrone.master"})
@Route(value = "EndPoint", layout = MainLayout.class)
public class EndPointForm extends TAForm<EndPoint>  {

    private TextField target;
    private TextField description;

    @Inject
    EndPointsService endPointsService;

    public EndPointForm() {
        target = UIUtils.getTextField("EndPoint.target");
        description = UIUtils.getTextField("EndPoint.description");

        addBodyComponets("separator.general", target, description);
    }

}
