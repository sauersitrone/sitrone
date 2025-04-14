package de.simone.ui.components;

import com.vaadin.flow.component.shared.ThemeVariant;

public enum TColorPickerVariant implements ThemeVariant {
    COMPACT("compact"), LUMO_SMALL("small"), LUMO_ALIGN_LEFT(
            "align-left"), LUMO_ALIGN_CENTER("align-center"), LUMO_ALIGN_RIGHT(
                    "align-right"), LUMO_HELPER_ABOVE_FIELD(
                            "helper-above-field");

    private final String variant;

    TColorPickerVariant(String variant) {
        this.variant = variant;
    }

    @Override
    public String getVariantName() {
        return variant;
    }
}