package de.simone.ui;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.UIUtils;
import de.simone.ui.components.HanniResourceCenter;

public class HomeDashboard extends Div {

    private Div headerDiv;
    private Div bodyDiv;

    public HomeDashboard() {
        addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.Gap.MEDIUM);

        this.headerDiv = new Div();
        // headerDiv.addClassNames(LumoUtility.Display.GRID,
        // LumoUtility.Grid.Column.COLUMNS_4, LumoUtility.Gap.MEDIUM,
        // LumoUtility.FlexWrap.WRAP);
        headerDiv.addClassNames("homeDashboard-headerDiv");

        this.bodyDiv = new Div();
        bodyDiv.addClassNames("homeDashboard-bodyDiv");
        // bodyDiv.addClassNames(LumoUtility.Display.GRID, LumoUtility.Grid.Row.ROWS_2,
        // LumoUtility.Grid.Column.COLUMNS_2,
        // LumoUtility.Gap.MEDIUM);

        add(headerDiv, bodyDiv);
    }

    public void setBody(HanniResourceCenter hanni, List<VerticalLayout> components) {
        bodyDiv.add(hanni);
        if (components.size() > 0) // NOSONAR
        bodyDiv.add(components.get(0));
    }

    public void addHeaderWidget(Component sticker) {
        VerticalLayout wrap = UIUtils.getCompactVerticalLayout(sticker);
        wrap.getStyle().set("background-color", sticker.getStyle().get("background-color"));
        wrap.addClassName(LumoUtility.Background.CONTRAST_10);
        wrap.setSizeFull();
        // sticker.setSizeFull();
        headerDiv.add(sticker);
    }

}
