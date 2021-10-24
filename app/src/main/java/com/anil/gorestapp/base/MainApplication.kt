package com.anil.gorestapp.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.anil.gorestapp.base.network.ConnectivityReceiver
import com.anil.gorestapp.di.component.ApplicationComponent
import com.anil.gorestapp.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import kotlin.properties.Delegates


open class MainApplication : Application(),HasAndroidInjector {

    private lateinit var appComponent: ApplicationComponent
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        initDagger()
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
    open fun initDagger() {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector


}