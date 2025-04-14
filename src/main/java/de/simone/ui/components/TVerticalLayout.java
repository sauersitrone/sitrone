package de.simone.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TVerticalLayout extends VerticalLayout {

    public TVerticalLayout(Component... components) {
        super(components);
        setPadding(false);
        // setSpacing(false); // this component is used mostly as container for other forms components
        setMargin(false);
        setWidthFull();
    }    
}