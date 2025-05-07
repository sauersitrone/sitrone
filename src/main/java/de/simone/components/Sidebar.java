package de.simone.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIcon;

public class Sidebar extends Section implements HasEnabled, HasTheme {

    private Header header;
    private H2 title;
    private Span description;

    private Layout content;

    private Footer footer;

    public Sidebar() {
        this("", "");

    }
    public Sidebar(String title, Component... components) {
        this(title, null, components);
    }

    public Sidebar(String title, String description, Component... components) {
        addClassNames(Background.BASE, BoxShadow.MEDIUM, Display.FLEX, FlexDirection.COLUMN, Overflow.HIDDEN,
                Position.FIXED, "bottom-0", "top-0", "transition-all", "z-10");
        setMaxWidth(100, Unit.PERCENTAGE);
        setWidth(580, Unit.PIXELS);

        this.header = new Header();
        this.header.addClassNames(Border.BOTTOM, Display.FLEX, JustifyContent.BETWEEN,
                Padding.End.MEDIUM, Padding.Start.LARGE, Padding.Vertical.MEDIUM);
        add(this.header);

        createContent(components);

        this.footer = new Footer();
        this.footer.addClassNames(Background.CONTRAST_5, Display.FLEX, Gap.SMALL, JustifyContent.END,
                Padding.Horizontal.MEDIUM, Padding.Vertical.SMALL);
        add(this.footer);

        close();
    }

    public void createHeader(String title, String description) {
        this.title = new H2(title);
        this.title.addClassNames(FontSize.XLARGE);

        Layout layout = new Layout(this.title);
        layout.setFlexDirection(Layout.FlexDirection.COLUMN);
        layout.setGap(Layout.Gap.SMALL);

        if (description != null) {
            this.description = new Span(description);
            this.description.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
            layout.add(this.description);
        }

        Button close = new Button(LineAwesomeIcon.TIMES_SOLID.create(), e -> close());
        close.addClassNames(Margin.Vertical.NONE);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.setAriaLabel("Close sidebar");
        close.setTooltipText("Close sidebar");

        header.removeAll();
        this.header.add(layout, close);

        if (description == null) {
            this.header.addClassNames(AlignItems.CENTER);
        }
    }

    private void createContent(Component... components) {
        this.content = new Layout(components);
        this.content.addClassNames(Flex.GROW, Padding.Bottom.MEDIUM, Padding.Horizontal.LARGE, Padding.Top.SMALL);
        this.content.setFlexDirection(Layout.FlexDirection.COLUMN);
        add(this.content);
    }

    public void setContent(List<Component> components) {
        content.removeAll();
        content.add(components);
        content.setOverflow(de.simone.components.Layout.Overflow.AUTO);
    }

    public void setFooter(List<Component> components) {
        footer.removeAll();
        footer.add(components);
    }

    // TODO: Refocus the component that opened the sidebar after closing
    public void close() {
        addClassNames("-end-full");
        removeClassName("end-0");
        setEnabled(false);
    }

    public void open() {
        addClassNames("end-0");
        removeClassNames("-end-full");
        setEnabled(true);
    }

    public void addHeaderThemeName(String theme) {
        this.header.getElement().getThemeList().add(theme);
    }

    public void removeHeaderThemeName(String theme) {
        this.header.getElement().getThemeList().remove(theme);
    }

    public Layout getContent() {
        return this.content;
    }

    public Footer getFooter() {
        return this.footer;
    }
}
