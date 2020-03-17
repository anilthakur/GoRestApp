package com.anil.gorestapp.utils.commonconstant

import android.util.Base64
import java.security.*
import java.security.spec.KeySpec
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by Anil Kumar on 2020-03-17
 */
object CryptUtil {
    private const val ALGORITHM = "AES"
    private const val MODE = "AES/CBC/PKCS5Padding"
    private const val KEY = "abc123"

    private const val IV_LENGTH_BYTES = 16
    private const val RANDOM_ALGORITHM = "SHA1PRNG"

    private val ivByteArray = generateIV()
    private val secretKeySpec: SecretKeySpec
    private val cipher: Cipher

    init {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)

        val spec: KeySpec = PBEKeySpec(KEY.toCharArray(), salt, 65536, 256) // AES-256

        val f: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val key: ByteArray = f.generateSecret(spec).getEncoded()
        secretKeySpec = SecretKeySpec(key, "AES")
        cipher = Cipher.getInstance(MODE)
    }

    @Throws(GeneralSecurityException::class)
    private fun generateIV(): ByteArray? {
        val random = SecureRandom()
        val bytes = ByteArray(IV_LENGTH_BYTES)
        random.nextBytes(bytes)
        return bytes
    }

    @Throws(NoSuchPaddingException::class, NoSuchAlgorithmException::class, InvalidAlgorithmParameterException::class, InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun encrypt(value: String): String? {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(ivByteArray))
        val values: ByteArray = cipher.doFinal(value.toByteArray())
        return Base64.encodeToString(values, Base64.DEFAULT)
    }

    @Throws(NoSuchPaddingException::class, NoSuchAlgorithmException::class, InvalidAlgorithmParameterException::class, InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun decrypt(value: String?): String? {
        val values: ByteArray = Base64.decode(value, Base64.DEFAULT)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(ivByteArray))
        return String(cipher.doFinal(values))
    }
}