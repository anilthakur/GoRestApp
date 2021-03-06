package com.anil.gorestapp

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.anil.gorestapp.base.network.ConnectivityReceiver
import com.anil.gorestapp.di.component.ApplicationComponent
import com.anil.gorestapp.di.component.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import kotlin.properties.Delegates


/**
 * Created by Anil Kumar on 2020-03-07
 */
class MainApplication : Application(), HasActivityInjector {
    private val TAG = MainApplication::class.java.name
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    private var appComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }


    override fun activityInjector() = activityInjector


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