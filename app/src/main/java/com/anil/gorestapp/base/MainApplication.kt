package com.anil.gorestapp.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.anil.gorestapp.base.network.ConnectivityReceiver
import com.anil.gorestapp.di.component.ApplicationComponent
import com.anil.gorestapp.di.component.DaggerApplicationComponent
import kotlin.properties.Delegates


class MainApplication : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        appComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    fun appComponent() = appComponent

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        var mInstance: MainApplication by Delegates.notNull()

        fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener) {
            ConnectivityReceiver.connectivityReceiverListener = listener
        }
    }
}