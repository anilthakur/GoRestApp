package com.anil.gorestapp.utils.commonconstant

import android.content.SharedPreferences
import com.anil.gorestapp.MainApplication

/**
 * Created by Anil Kumar on 2020-03-17
 */
object SecureSharedPrefUtils {

    private val encoding = Charsets.UTF_8
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = MainApplication.applicationContext().getSharedPreferences("APPLICATION_SECURED_PREF", android.content.Context.MODE_PRIVATE)
    }

    @JvmStatic
    fun putEncryptedString(key: String?, value: String?) {
        if (value == null) {
            sharedPreferences.edit().putString(key, null).apply()
        } else {
            try {
                val encryptedString = CryptUtil.encrypt(value)
                sharedPreferences.edit().putString(key, encryptedString).apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    fun getEncryptedString(key: String?, defaultValue: String? = ""): String? {
        return try {
            val encryptedValue: String? = sharedPreferences.getString(key, null)
            encryptedValue?.let {
                val decryptedValue = CryptUtil.decrypt(it)
                decryptedValue
            } ?: (defaultValue ?: "")
        } catch (e: java.lang.Exception) {
            (defaultValue ?: "")
        }
    }
}