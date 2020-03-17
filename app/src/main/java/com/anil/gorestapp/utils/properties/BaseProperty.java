package com.anil.gorestapp.utils.properties;

import androidx.annotation.NonNull;

/**
 * Created by jmpirie on 2016-09-04.
 */
public abstract class BaseProperty<T> {

    final AppProperties appProperties;
    String key;

    protected BaseProperty(AppProperties appProperties) {
        this(appProperties, null);
    }

    protected BaseProperty(AppProperties appProperties, String key) {
        this.appProperties = appProperties;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public final boolean isNotSet() {
        return !isSet();
    }

    public boolean isSet() {
        return appProperties.contains(key);
    }

    public T clear() {
        return set(null);
    }

    public final boolean isFor(BaseProperty baseProperty) {
        return isFor(baseProperty.key);
    }

    public final boolean isFor(String key) {
        return this.key != null ? this.key.equals(key) : key == null;
    }

    public final T get() {
        return getImpl();
    }

    public final T set(T t) {
        T t0 = get();
        if (t == null) {
            appProperties.clear(key);
        } else if (!t.equals(t0)) {
            setImpl(t);
        }
        T t1 = get();
        if (!areEqual(t0, t1)) {
            appProperties.notifyChanged(this);
        }
        return t1;
    }

    protected abstract T getImpl();

    protected abstract void setImpl(@NonNull T t);

    public final boolean isEqual(T t) {
        return areEqual(get(), t);
    }

    protected final boolean areEqual(T t1, T t2) {
        if (t1 != null && t2 != null) {
            return t1.equals(t2);
        } else return t1 == null && t2 == null;
    }
}
