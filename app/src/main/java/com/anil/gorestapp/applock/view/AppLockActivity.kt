package com.anil.gorestapp.applock.view

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import com.anil.gorestapp.R
import com.anil.gorestapp.applock.biomtericmanager.*
import com.anil.gorestapp.applock.widget.AppLockWidget
import com.anil.gorestapp.applock.widget.AppLockWidget.*
import com.anil.gorestapp.base.extenstion.observe
import com.anil.gorestapp.base.widget.Widget
import com.anil.gorestapp.preferences.AppPreference
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.view_app_lock_widget.*
import javax.inject.Inject

class AppLockActivity : AppCompatActivity() {

    @Inject
    lateinit var appLockWidget: AppLockWidget

    @Inject
    lateinit var appPreference: AppPreference

    private lateinit var cryptographyManager: CryptographyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_app_lock)
        initWidget(app_lock_container)
        showBiometricPromptForEncryption()
    }

    private fun initWidget(view: View) {
        appLockWidget.apply {
            initView(view)
            addWidget(this)
            show()
            observe(onClicked, ::onCreatePinClick)
        }
    }

    private fun addWidget(widget: Widget) {
        lifecycle.addObserver(widget)
    }

    private fun onCreatePinClick(callAction: CallToAction) {
        when (callAction) {
            is CallToAction.ValidateDigit -> {
                appPreference.saveAppLockPin(callAction.digitValue)
            }
        }

    }



    private fun showBiometricPromptForEncryption() {
        val canAuthenticate = BiometricManager.from(this).canAuthenticate()
        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            val secretKeyName = this.getString(R.string.secret_key_name)
            cryptographyManager = CryptographyManager()
            val cipher = cryptographyManager.getInitializedCipherForEncryption(secretKeyName)
            val biometricPrompt =
                BiometricPromptUtils.createBiometricPrompt(this, ::encryptAndStoreServerToken)
            val promptInfo = BiometricPromptUtils.createPromptInfo(this)
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

    private fun encryptAndStoreServerToken(authResult: BiometricPrompt.AuthenticationResult) {
        authResult.cryptoObject?.cipher?.apply {
            SampleAppUser.fakeToken?.let { token ->
                Log.d(ContentValues.TAG, "The token from server is $token")
                val encryptedServerTokenWrapper = cryptographyManager.encryptData(token, this)
                cryptographyManager.persistCiphertextWrapperToSharedPrefs(
                    encryptedServerTokenWrapper,
                    applicationContext,
                    SHARED_PREFS_FILENAME,
                    Context.MODE_PRIVATE,
                    CIPHERTEXT_WRAPPER
                )
            }
        }
        finish()
    }


}