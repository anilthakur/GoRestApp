package com.anil.gorestapp

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.anil.gorestapp.base.network.ConnectivityReceiver
import com.anil.gorestapp.di.component.ApplicationComponent
import com.anil.gorestapp.di.component.DaggerApplicationComponent
import kotlin.properties.Delegates


/**
 * Created by Anil Kumar on 2020-03-07
 */
class MainApplication : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        appComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
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