package de.simone.ui.login;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.UIUtils;

public abstract class GALoginForm extends VerticalLayout {

    public static final String FIELD_SIZE = "11em";
    protected ExternalLogin externalLogin;
    protected Button button;
    protected boolean allowBack = true;

    GALoginForm(ExternalLogin login) {
        super();
        this.externalLogin = login;
        this.button = UIUtils.getButton(getClass().getSimpleName() + ".accept",
                ButtonVariant.LUMO_PRIMARY);
        this.button.addClickListener(ent -> accept());
        button.addClickShortcut(Key.ENTER);
    }

    public Component getTitle() {
      SvgIcon icon = LineAwesomeIcon.CHEVRON_LEFT_SOLID.create();
        Button back = new Button(icon, ev -> externalLogin.moveBack());
        back.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        back.getStyle().set("margin-right", "auto"); // <--
        String title = getTranslation(getClass().getSimpleName() + ".title");
        Span label = new Span(title);
        label.setWidthFull();
        Component titDiv = allowBack ? new H3(back, label) : new H3(label);
        return titDiv;
    }

    /**
     * return standar body loging component
     * 
     * @param txt        - text to add as description to the page. my be null
     * @param components - aditionals components to add
     * 
     * @return {@link VerticalLayout}
     */
    protected void configureBody(Component... components) {
        String txt = getClass().getSimpleName() + ".message";
        add(getExplanation(txt));
        if (components != null) {
            add(components);
        }
        setPadding(false);
        setSpacing(false);
        setAlignItems(FlexComponent.Alignment.STRETCH);
    }

    /**
     * standar method invoqued by acept button. subclass muss override this method
     * to contol the workflow based on the user interaction
     */
    abstract void accept();

    Component[] getFooter() {
        return new Component[] { button };
    }

    protected Component getExplanation(String bundleId) {
        Span text = new Span();
        String exp = text.getTranslation(bundleId);
        // if explanation is not found, return empty component
        if (exp.startsWith("!{"))
            return text;

        text.setText(exp);
        text.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);
        return text;
    }

    protected Component getExplanation2(String bundleId) {
        Span text = new Span();
        String exp = text.getTranslation(bundleId);
        // if explanation is not found, return empty component
        if (exp.startsWith("!{"))
            return text;

        text.getElement().setProperty("innerHTML", exp.startsWith("<p>") ? exp : "<p>" + exp);

        return text;
    }

}
