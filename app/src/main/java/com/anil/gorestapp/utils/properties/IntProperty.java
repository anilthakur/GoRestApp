package com.anil.gorestapp.utils.properties;

public class IntProperty extends BaseProperty<Integer> {

    private final int defaultValue;

    public IntProperty(AppProperties appProperties, String key, int defaultValue) {
        super(appProperties, key);
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer getImpl() {
        return appProperties.getInt(key, defaultValue);
    }

    @Override
    public void setImpl(Integer value) {
        appProperties.setInt(key, value);
    }
}
