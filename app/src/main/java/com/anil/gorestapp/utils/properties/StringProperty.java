package com.anil.gorestapp.utils.properties;

import androidx.annotation.NonNull;

public class StringProperty extends BaseProperty<String> {

    private final String defaultValue;

    public StringProperty(AppProperties appProperties, String key, String defaultValue) {
        super(appProperties, key);
        this.defaultValue = defaultValue;
    }

    @Override
    public String getImpl() {
        return appProperties.getString(key, defaultValue);
    }

    @Override
    public void setImpl(@NonNull String value) {
        appProperties.setString(key, value);
    }
}
