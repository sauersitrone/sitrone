package de.simone.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.UIUtils;

public class GroupPanel extends TVerticalLayout {

    private VerticalLayout headerLayout = new VerticalLayout();
    private FormLayout bodyLayout;
    public String titleId;

    public GroupPanel(String titleId, Component... components) {
        this(titleId, false, components);
    }

    public GroupPanel(String titleId, boolean withHelp, Component... components) {
        this.titleId = titleId;
        if (titleId != null)
            this.headerLayout = UIUtils.getTitleH(titleId, withHelp);
        else
            headerLayout.setVisible(false);

        headerLayout.setPadding(false);
        headerLayout.setSpacing(false);
        headerLayout.setMargin(false);

        this.bodyLayout = new FormLayout(components);
        UIUtils.setResponsiveSteps(bodyLayout, 3);

        bodyLayout.addClassName(LumoUtility.Margin.MEDIUM);
        bodyLayout.addClassName(LumoUtility.Margin.Top.NONE);
        UIUtils.setGroupStyle(this);
        add(headerLayout, bodyLayout);
    }

    public void setBodyComponents(Component... components) {
        bodyLayout.removeAll();
        bodyLayout.add(components);
    }

    public List<Component> getBodyComponents() {
        List<Component> components = new ArrayList<>();
        bodyLayout.getChildren().forEach(components::add);
        return components;
    }

    public void setColums(int columns) {
        UIUtils.setResponsiveSteps(bodyLayout, columns);
    }

    public FormLayout getBodyLayout() {
        return bodyLayout;
    }
}
