package de.simone.views.components;

import de.simone.MainLayout;
import de.simone.components.Breadcrumb;
import de.simone.components.BreadcrumbItem;
import de.simone.views.HomeView;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PermitAll
@PageTitle("Breadcrumbs")
@Route(value = "breadcrumbs", layout = MainLayout.class)
public class BreadcrumbsView extends ComponentView {

    public BreadcrumbsView() {
        addClassNames(Padding.Top.LARGE);

        add(new Breadcrumb(
                new BreadcrumbItem("Home", HomeView.class),
                new BreadcrumbItem("Breadcrumbs", BreadcrumbsView.class)
        ));
    }

}
