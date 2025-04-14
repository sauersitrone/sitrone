package de.simone.components;

import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

public class Breadcrumb extends Nav {

    private OrderedList list;

    public Breadcrumb() {
        addClassName("breadcrumb");
        setAriaLabel("Breadcrumb");

        this.list = new OrderedList();
        this.list.addClassNames(Display.FLEX, FontSize.SMALL, ListStyleType.NONE, Margin.Vertical.NONE,
                Padding.Start.NONE);
        add(this.list);
    }

    public Breadcrumb(BreadcrumbItem... items) {
        this();
        this.list.add(items);
    }

    public void add(BreadcrumbItem... items) {
        this.list.add(items);
    }

    public void remove(BreadcrumbItem... items) {
        this.list.remove(items);
    }

    @Override
    public void removeAll() {
        this.list.removeAll();
    }
}
