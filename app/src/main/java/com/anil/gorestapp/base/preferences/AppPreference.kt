package com.anil.gorestapp.base.preferences

interface AppPreference {
    var userName: String
    fun getWebsite(): String
    fun setWebsite(website: String)
}