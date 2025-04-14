package de.simone.components;

import de.simone.utilities.BorderColor;
import de.simone.utilities.Color;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.theme.lumo.LumoUtility.*;
import org.vaadin.lineawesome.LineAwesomeIcon;

// TODO: Needs a new name to avoid clashing with the default.
public class Notification extends Layout implements HasTheme {

    private BorderColor borderColor;

    private Type type;
    private Layout icon;
    private Layout row;
    private Layout column;
    private Span title;
    private Div description;
    private Layout actions;

    public Notification(String title, String description) {
        this(title, new Text(description), Type.INFO);
    }

    public Notification(String title, String description, Type type) {
        this(title, new Text(description), type);
    }

    public Notification(String title, Component description) {
        this(title, description, Type.INFO);
    }

    public Notification(String title, Component description, Type type) {
        addClassNames("notification", Background.BASE, BorderRadius.MEDIUM, Padding.SMALL);
        setGap(Layout.Gap.SMALL);
        setType(type);

        this.title = new Span(title);
        this.title.addClassNames(FontWeight.SEMIBOLD);

        this.description = new Div(description);
        this.description.addClassNames(FontSize.SMALL, TextColor.SECONDARY);

        this.column = new Layout(this.title, this.description);
        this.column.addClassNames(Margin.Vertical.XSMALL);
        this.column.setFlexDirection(FlexDirection.COLUMN);
        this.column.setFlexGrow();
        this.column.setGap(Gap.XSMALL);

        this.actions = new Layout();
        this.actions.setGap(Layout.Gap.SMALL);
        setActions(null);

        this.row = new Layout(this.column, this.actions);
        this.row.setFlexGrow();
        this.row.setFlexWrap(FlexWrap.WRAP);
        this.row.setGap(Layout.Gap.XSMALL);

        add(this.icon, this.row);
    }

    /**
     * Applies a border around the notification.
     */
    public void setBorder(boolean border) {
        if (border) {
            addClassNames(Border.ALL);
        } else {
            removeClassNames(Border.ALL);
        }
    }

    /**
     * Applies a left (LTR) or right (RTL) border on the notification.
     * TODO: Needs a better name.
     */
    public void setAccentBorder(boolean border) {
        if (border) {
            getStyle().setBorderLeft("var(--lumo-space-xs) solid var(--lumo-utility-border-color)");
            this.icon.removeClassName(Margin.Start.XSMALL);
        } else {
            getStyle().remove("border-left");
            this.icon.addClassName(Margin.Start.XSMALL);
        }
    }

    /**
     * Sets the border color.
     */
    private void setBorderColor(BorderColor borderColor) {
        if (this.borderColor != null) {
            removeClassNames(this.borderColor.getClassName());
        }
        addClassNames(borderColor.getClassName());
        this.borderColor = borderColor;
    }

    /**
     * Sets the text color.
     */
    public void setTextColor(Color.Text color) {
        this.description.removeClassName(TextColor.SECONDARY);

        this.title.addClassNames(color.getClassName());
        this.description.addClassNames(color.getClassName());
    }

    /**
     * Sets the type (info, success or error).
     */
    private void setType(Type type) {
        this.type = type;
        switch (type) {
            case INFO:
            default:
                setBorderColor(BorderColor.PRIMARY_50);
                getElement().setAttribute("role", "status");
                getStyle().set("background-image", "linear-gradient(var(--lumo-primary-color-10pct), var(--lumo-primary-color-10pct))");
                setIcon(LineAwesomeIcon.INFO_CIRCLE_SOLID, Color.Text.PRIMARY);
                break;
            case SUCCESS:
                setBorderColor(BorderColor.SUCCESS_50);
                getElement().setAttribute("role", "status");
                getStyle().set("background-image", "linear-gradient(var(--lumo-success-color-10pct), var(--lumo-success-color-10pct))");
                setIcon(LineAwesomeIcon.CHECK_CIRCLE, Color.Text.SUCCESS);
                break;
            case ERROR:
                setBorderColor(BorderColor.ERROR_50);
                getElement().setAttribute("role", "alert");
                getStyle().set("background-image", "linear-gradient(var(--lumo-error-color-10pct), var(--lumo-error-color-10pct))");
                setIcon(LineAwesomeIcon.EXCLAMATION_CIRCLE_SOLID, Color.Text.ERROR);
                break;
        }
    }

    /**
     * Sets the icon.
     */
    public void setIcon(LineAwesomeIcon icon, Color.Text color) {
        SvgIcon i = icon.create();

        this.icon = new Layout(i);
        this.icon.addClassNames(color.getClassName(), Flex.SHRINK_NONE, Height.XSMALL, Margin.Top.XSMALL,
                Margin.Start.XSMALL, Width.XSMALL);
        this.icon.setAlignItems(AlignItems.CENTER);
        this.icon.setJustifyContent(JustifyContent.CENTER);
    }

    /**
     * Sets the title.
     */
    public void setTitle(String text) {
        this.title.setText(text);
    }

    /**
     * Sets the description.
     */
    public void setDescription(Component... components) {
        this.description.removeAll();
        if (components != null) {
            for (Component component : components) {
                if (component != null) {
                    this.description.add(component);
                }
            }
        }
        this.description.setVisible(this.description.getComponentCount() > 0);
    }

    /**
     * Sets the actions.
     */
    public void setActions(Component... components) {
        this.actions.removeAll();
        if (components != null) {
            for (Component component : components) {
                if (component != null) {
                    if (component instanceof Button) {
                        themeButton((Button) component);
                    }
                    this.actions.add(component);
                }
            }
        }
        this.actions.setVisible(this.actions.getComponentCount() > 0);
    }

    private void themeButton(Button button) {
        if (this.type.equals(Notification.Type.SUCCESS)) {
            button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        } else if (this.type.equals(Notification.Type.ERROR)) {
            button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        }
        button.addClassNames(Margin.Vertical.NONE, Padding.Horizontal.MEDIUM);
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    }

    public enum Type {
        INFO, SUCCESS, ERROR
    }
}
