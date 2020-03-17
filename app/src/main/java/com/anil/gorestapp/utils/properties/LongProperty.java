package com.anil.gorestapp.utils.properties;

import androidx.annotation.NonNull;


public class LongProperty extends BaseProperty<Long> {

    private final long defaultValue;

    public LongProperty(AppProperties appProperties, String key, long defaultValue) {
        super(appProperties, key);
        this.defaultValue = defaultValue;
    }

    @Override
    protected Long getImpl() {
        return appProperties.getLong(key, defaultValue);
    }

    @Override
    protected void setImpl(@NonNull Long value) {
        appProperties.setLong(key, value);
    }
}
