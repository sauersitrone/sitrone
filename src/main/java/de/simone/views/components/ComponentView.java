package de.simone.views.components;

import de.simone.components.Preview;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

public class ComponentView extends Main {

    public ComponentView() {
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);
    }

    public void addH2(String text) {
        H2 h2 = new H2(text);
        h2.addClassNames(FontSize.LARGE, Margin.Bottom.MEDIUM, Margin.Top.LARGE);
        add(h2);
    }

    public void addPreview(Component... components) {
        add(new Preview(components));
    }

    
  public static Component getH2(String text, String help) {
    H2 h2 = new H2(text);
    // h2.addClassNames(FontSize.LARGE, Margin.Bottom.MEDIUM, Margin.Top.LARGE);
    h2.addClassNames(FontSize.LARGE);
    h2.setId(text.replace(" ", "-").toLowerCase());

    Component component = h2;
    if (help != null) {
      Paragraph description = new Paragraph(help);
      description.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
      component = new Div(h2, description);
    }

    return component;
  }


}
