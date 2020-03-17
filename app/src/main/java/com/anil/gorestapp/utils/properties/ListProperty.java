package com.anil.gorestapp.utils.properties;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;


public class ListProperty extends BaseProperty<List<String>> {

    public ListProperty(AppProperties appProperties, String key) {
        super(appProperties, key);
    }

    @Override
    protected List<String> getImpl() {
        Gson gson = new Gson();
        String json = appProperties.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        List<String> arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        return arrayList;
    }

    @Override
    protected void setImpl(@NonNull List<String> strings) {
        Gson gson = new Gson();
        String json = gson.toJson(strings);
        appProperties.setString(key, json);
    }

    public int size() {
        List<String> list = get();
        return list != null ? list.size() : 0;
    }
}
