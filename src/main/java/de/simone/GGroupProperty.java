
package de.simone;

public class GGroupProperty {

    public String bundleKey;
    public String group;
    public int order;
    public String key;
    public String value;
    public boolean isDisabled;

    public GGroupProperty() {
        group = "";
        this.order = -1;
        key = "";
        value = "";
        isDisabled = true;
    }

    public static String[] parseBundleKey(String bundleKey) {
        String[] prp = bundleKey.split("-");
        return prp;
    }

    public GGroupProperty(String bundleKey, String value) {
        this.bundleKey = bundleKey;
        String[] prp = parseBundleKey(bundleKey);
        this.group = prp[0];
        this.order = Integer.parseInt(prp[1]);
        this.key = prp[2];
        this.value = value;
        if (order == 0)
            isDisabled = true;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }
}
