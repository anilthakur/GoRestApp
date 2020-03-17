package com.anil.gorestapp.utils.properties;

public final class MarkProperty extends BooleanProperty {

    public MarkProperty(AppProperties appProperties, String key) {
        super(appProperties, key, false, !STORE_NEGATED);
    }

    public MarkProperty(AppProperties appProperties, String key, boolean storeNegated) {
        super(appProperties, key, false, storeNegated);
    }

    public void mark() {
        appProperties.setBoolean(key, true);
    }

    public boolean isMarked() {
        return appProperties.getBoolean(key, false);
    }
}
