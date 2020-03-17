package com.anil.gorestapp.utils.commonconstant

import timber.log.Timber
import java.security.SecureRandom
import android.util.Base64
import java.util.*

/**
 * Created by Anil Kumar on 2020-03-17
 */
class RealmSecureKeyProvider {

    fun getRealmKey(): ByteArray? {
        val key: ByteArray?
        val savedKey: String? = getStringFromPrefs(REALM_KEY)
        if (savedKey == null || (savedKey.isEmpty())) {
            key = generateKey()
            val keyString: String? = encodeToString(key)
            saveStringToPrefs(keyString)
        } else {
            key = decodeFromString(savedKey)
        }
        return key
    }

    private fun getStringFromPrefs(aKey: String, defaultValue: String = ""): String? {
        return SecureSharedPrefUtils.getEncryptedString(aKey, defaultValue)
    }

    private fun saveStringToPrefs(aKeyString: String?) {
        SecureSharedPrefUtils.putEncryptedString(REALM_KEY, aKeyString)
    }

    private fun encodeToString(aKey: ByteArray?): String? {
        Timber.d("Jai: Realm Encoding Key: %s", Arrays.toString(aKey))
        return Base64.encodeToString(aKey, Base64.DEFAULT)
    }

    private fun decodeFromString(aSavedKey: String): ByteArray? {
        val decoded: ByteArray = Base64.decode(aSavedKey, Base64.DEFAULT)
        Timber.d("Jai: Realm Decoded Key: %s", Arrays.toString(decoded))
        return decoded
    }

    private fun generateKey(): ByteArray? {
        val key = ByteArray(64)
        SecureRandom().nextBytes(key)
        return key
    }

    companion object {
        private const val REALM_KEY = "com.tesco.clubcardmobile.realmkey"
    }
}