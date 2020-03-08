package com.anil.gorestapp.data.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by Anil Kumar on 2020-03-07
 */
class PersonConverter {

    @TypeConverter
    fun fromStringToAvailabilitySettingsList(value: String?): ArrayList<ResultItem>? {
        val listType: Type = object : TypeToken<ArrayList<ResultItem>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromAvailabilitySettingsListToString(list: ArrayList<ResultItem?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromStringMeta(value: String?): Meta? {
        val listType: Type = object : TypeToken<Meta?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromMetaToString(list: Meta?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}