package de.simone.views;

import de.simone.MainLayout;
import de.simone.views.components.ComponentView;
import jakarta.annotation.security.PermitAll;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PermitAll
@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
public class HomeView extends ComponentView {

    public HomeView() {
        addClassNames(Padding.Top.LARGE);

        add(new Paragraph("Welcome to Vaadin+!"));
    }

}
