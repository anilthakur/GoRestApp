package com.anil.gorestapp.utils.properties;

public class BooleanProperty extends BaseProperty<Boolean> {

    public static final boolean STORE_NEGATED = true;

    final boolean defaultValue;
    final boolean storeNegated;

    public BooleanProperty(AppProperties appProperties, String key, boolean defaultValue) {
        this(appProperties, key, defaultValue, !STORE_NEGATED);
    }

    public BooleanProperty(AppProperties appProperties, String key, boolean defaultValue, boolean storeNegated) {
        super(appProperties, key);
        this.defaultValue = defaultValue;
        this.storeNegated = storeNegated;
    }

    public Boolean toggle() {
        return set(!get());
    }

    @Override
    protected Boolean getImpl() {
        return appProperties.getBoolean(key, defaultValue ^ storeNegated) ^ storeNegated;
    }

    @Override
    protected void setImpl(Boolean enabled) {
        appProperties.setBoolean(key, enabled ^ storeNegated);
    }
}
