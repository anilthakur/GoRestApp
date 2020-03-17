package com.anil.gorestapp.utils.properties;

public class EnabledProperty extends BooleanProperty {

    public EnabledProperty(AppProperties appProperties, String key, boolean defaultValue) {
        this(appProperties, key, defaultValue, false);
    }

    public EnabledProperty(AppProperties appProperties, String key, boolean defaultValue, boolean storeNegated) {
        super(appProperties, key, defaultValue, storeNegated);
    }

    public boolean enable() {
        return set(true);
    }

    public boolean disable() {
        return set(false);
    }

    public boolean isEnabled() {
        return get();
    }

    public boolean isDisabled() {
        return !isEnabled();
    }
}
