package de.simone.ui.components;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.backend.TAEntity;
import de.simone.backend.User;

public class ClickableCardMedium extends HorizontalLayout {

    private HorizontalLayout imageLayout;
    private VerticalLayout textLayout;
    private Span titleLabel;
    private Span textLabel;
    private boolean selected;
    private Component imageIcon;

    public ClickableCardMedium() {
        UIUtils.setCompatStyle(this);
        getElement().addEventListener("mouseenter", ev -> setHover(true));
        getElement().addEventListener("mouseleave", ev -> setHover(false));
        this.imageLayout = UIUtils.getCompactHorizontalLayout(true);
        imageLayout.setMaxWidth("15%");
        imageLayout.addClassNames(LumoUtility.Background.CONTRAST_5, LumoUtility.Border.RIGHT,
                LumoUtility.BorderColor.CONTRAST_10);

        this.titleLabel = new Span("");
        titleLabel.addClassName(LumoUtility.FontWeight.BOLD);
        this.textLabel = new Span("");

        this.textLayout = UIUtils.getCompactVerticalLayout(titleLabel, textLabel);
        textLayout.setAlignItems(Alignment.STRETCH);
        textLayout.setWidthFull();
        textLayout.setPadding(true);

        addClassNames(LumoUtility.Border.ALL, LumoUtility.BorderRadius.SMALL, LumoUtility.BorderColor.CONTRAST_10);

        setHover(false);
        add(imageLayout, textLayout);
    }

    public static ClickableCardMedium getFromBundleKey(String bundleKey, String id,
            ComponentEventListener<ClickEvent<HorizontalLayout>> listener) {
        ClickableCardMedium card = new ClickableCardMedium();

        // imag or icon
        String imageName = card.getTranslation(bundleKey + ".image");
        String iconName = card.getTranslation(bundleKey + ".icon");
        if (!StringUtils.isBlank(imageName)) {
            card.imageIcon = UIUtils.getImageIcon(imageName, null, null);
        } else {
            card.imageIcon = UIUtils.getLaIcon("la-2x", iconName);
        }
        card.imageLayout.add(card.imageIcon);
        card.imageLayout.setMaxWidth("10%");

        card.titleLabel.setText(card.getTranslation(bundleKey + ".title"));
        card.textLabel.setText(card.getTranslation(bundleKey + ".text"));

        if (id != null)
            card.setId(id);

        if (listener != null)
            card.addClickListener(listener);

        return card;
    }

    public void setEntity(TAEntity entity) {
        String iconSize = "la-2x";
        int maxLen = 55;
        User user = SecurityUtils.getLoggedUser();

    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        titleLabel.removeClassNames(LumoUtility.TextColor.BODY, LumoUtility.TextColor.DISABLED);
        titleLabel.addClassNames(enabled ? LumoUtility.TextColor.BODY : LumoUtility.TextColor.DISABLED);

        textLabel.removeClassNames(LumoUtility.TextColor.BODY, LumoUtility.TextColor.DISABLED);
        textLabel.addClassNames(enabled ? LumoUtility.TextColor.BODY : LumoUtility.TextColor.DISABLED);

        if (imageIcon instanceof Span) {
            Span span = (Span) imageIcon;
            span.removeClassNames(LumoUtility.TextColor.BODY, LumoUtility.TextColor.DISABLED);
            span.addClassName(enabled ? LumoUtility.TextColor.BODY : LumoUtility.TextColor.DISABLED);
        } else {
            Image image = (Image) imageIcon;
            image.getStyle().remove("filter");
            if (!enabled)
                image.getStyle().set("filter", "opacity(0.25)");
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        removeClassNames(LumoUtility.Background.BASE, LumoUtility.Background.PRIMARY_10);
        addClassName(selected ? LumoUtility.Background.PRIMARY_10 : LumoUtility.Background.BASE);

        titleLabel.removeClassNames(LumoUtility.TextColor.PRIMARY, LumoUtility.TextColor.BODY);
        titleLabel.addClassName(selected ? LumoUtility.TextColor.PRIMARY : LumoUtility.TextColor.BODY);

        if (imageIcon instanceof Span) {
            ((Span) imageIcon).removeClassNames(LumoUtility.TextColor.PRIMARY, LumoUtility.TextColor.BODY);
            ((Span) imageIcon).addClassName(selected ? LumoUtility.TextColor.PRIMARY : LumoUtility.TextColor.BODY);
        }

    }

    private void setHover(boolean hover) {
        removeClassNames(LumoUtility.Background.CONTRAST_5, LumoUtility.Background.BASE);
        addClassName(hover ? LumoUtility.Background.CONTRAST_5 : LumoUtility.Background.BASE);
    }
}
