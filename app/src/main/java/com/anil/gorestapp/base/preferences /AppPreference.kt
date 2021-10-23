package com.anil.gorestapp.preferences

interface AppPreference {
    var userName: String
    fun getWebsite(): String
    fun setWebsite(website: String)
}