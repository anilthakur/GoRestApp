package com.anil.gorestapp.di.component

import android.app.Application
import com.android.artgallery.di.module.ActivityModule
import com.anil.gorestapp.base.MainApplication
import com.anil.gorestapp.di.module.ApplicationModule
import com.anil.gorestapp.di.module.NetworkModule
import com.anil.gorestapp.di.scope.PerApplication
import com.anil.gorestapp.person.injection.MainActivityBinding
import com.anil.gorestapp.person.view.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@PerApplication
@Component(
    modules = [
        ApplicationModule::class,
        ActivityModule::class,
        NetworkModule::class,
        MainActivityBinding::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MainApplication)
}