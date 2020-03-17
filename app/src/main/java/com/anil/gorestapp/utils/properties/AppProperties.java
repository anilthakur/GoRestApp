package com.anil.gorestapp.utils.properties;

import android.content.SharedPreferences;

import com.anil.gorestapp.utils.commonconstant.SecureSharedPrefUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.StringDef;
import rx.Observable;
import rx.subjects.PublishSubject;


public final class AppProperties {

    @StringDef({RetentionScope.INSTALLATION_SCOPE, RetentionScope.USER_SCOPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RetentionScope {
        String INSTALLATION_SCOPE = "INSTALLATION_SCOPE";
        String USER_SCOPE = "USER_SCOPE";
    }

    protected List<BaseProperty> installationScopeProperties = new ArrayList<>();
    protected List<BaseProperty> userScopeProperties = new ArrayList<>();

    protected PublishSubject<BaseProperty> propertiesSubject = PublishSubject.create();
    private SharedPreferences sharedPreferences;

    public Observable<BaseProperty> properties() {
        return Observable.merge(
                Observable.from(installationScopeProperties),
                Observable.from(userScopeProperties),
                propertiesSubject);
    }

    protected <T extends BaseProperty> T addProperty(T property, @RetentionScope String retentionScope) {
        if (retentionScope.equals(RetentionScope.INSTALLATION_SCOPE)) {
            installationScopeProperties.add(property);
        } else {
            userScopeProperties.add(property);
        }
        return property;
    }

    public void clearUserScope() {
        for (BaseProperty property : userScopeProperties) {
            property.clear();
        }
    }

    public final StringProperty selectedClubcardNumber = addProperty(new StringProperty(this, "SelectedClubcardNumber", null), RetentionScope.USER_SCOPE);
    public final IntProperty currentOrderByCategory = addProperty(new IntProperty(this, "CURRENT_OREDER_BY_CATEGORY", 0), RetentionScope.USER_SCOPE);

    public final BooleanProperty shouldShowNewAboutClubcardPointsVarient = addProperty(new BooleanProperty(this, "shouldShowNewAboutClubcardPointsVarient", false), RetentionScope.INSTALLATION_SCOPE);


    //User key
    public final StringProperty userKey = addProperty(new StringProperty(this, "userKey", ""),
            RetentionScope.USER_SCOPE);

    public AppProperties(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    protected void notifyChanged(BaseProperty property) {
        propertiesSubject.onNext(property);
    }

    public void clear(String key) {
        sharedPreferences.edit()
                .remove(key)
                .apply();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    protected boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    void setBoolean(String key, boolean value) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    String getString(String key, String defaultValue) {
        return SecureSharedPrefUtils.getEncryptedString(key, defaultValue);
    }

    void setString(String key, String value) {
        SecureSharedPrefUtils.putEncryptedString(key, value);
    }

    int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    void setInt(String key, int value) {
        sharedPreferences.edit()
                .putInt(key, value)
                .apply();
    }

    long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    void setLong(String key, long value) {
        sharedPreferences.edit()
                .putLong(key, value)
                .apply();
    }

    public void clear() {
        sharedPreferences.edit()
                .clear()
                .apply();
    }

    float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    void setFloat(String key, float value) {
        sharedPreferences.edit()
                .putFloat(key, value)
                .apply();
    }
}
