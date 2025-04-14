/*
 * Copyright (c) 2023-2024 GoodDEV
 * Alle Rechte vorbehalten.
 * 
 * Dieser Quellcode ist das geistige Eigentum von GoodDEV.
 * Es ist nicht gestattet, diesen Code ohne Genehmigung zu reproduzieren oder zu modifizieren.
 * 
 * Created Date: Fr Oct 2023
 * Author: Terry
 */

package de.simone.ui.components;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.TranslationProvider;
import de.simone.UIUtils;

public class HanniTaskItem extends HorizontalLayout {

    private VerticalLayout column;
    private Span primaryLabel;

    public HanniTaskItem(String task, boolean completed) {
        addClassName(LumoUtility.LineHeight.SMALL);

        String primary = TranslationProvider.getTranslation("task." + task + ".title");
        String secondary = TranslationProvider.getTranslation("task." + task + ".description");
        this.column = getPrimaryAndSecondaryComlumn(primary, secondary);

        Span icon = null;

        if (completed) {
            icon = UIUtils.getLaIcon("la-2x", "las la-check-circle");
            icon.addClassName(LumoUtility.TextColor.SUCCESS);
            primaryLabel.addClassName(LumoUtility.TextColor.SUCCESS);
        } else {
            icon = UIUtils.getLaIcon("la-2x", "las la-circle");
        }
        add(icon, column);
    }

    private VerticalLayout getPrimaryAndSecondaryComlumn(String primary, String secondary) {
        primaryLabel = new Span(primary);
        Span secondaryLabel = UIUtils.getSpan(secondary, true);
        secondaryLabel.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.SMALL);
        VerticalLayout column2 = UIUtils.getCompactVerticalLayout(primaryLabel, secondaryLabel);
        primaryLabel.setWidthFull();
        secondaryLabel.setWidthFull();
        return column2;
    }
}
