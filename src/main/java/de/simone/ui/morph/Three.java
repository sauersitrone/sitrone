package de.simone.ui.morph;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationListener;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import jakarta.annotation.security.RolesAllowed;

@Tag("div")
// @JsModule("./misc/three-test.js")
@JsModule("./misc/morph.js")
@RolesAllowed({"Zitrone.master", "Three"})
@NpmPackage(value = "three", version = "0.174.0 ")
@Route(value = "Three", layout = MainLayout.class)
public class Three extends Component implements AfterNavigationListener {

  public Three() {
    //
    getElement().executeJs("window.initThree($0)", this);
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    System.out.println("Three.afterNavigation()");
    getElement().executeJs("window.initThree($0)", this);
  }
}
