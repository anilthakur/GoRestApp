package com.anil.gorestapp.di.component

import android.app.Application
import com.anil.gorestapp.di.module.ActivityModule
import com.anil.gorestapp.base.MainApplication
import com.anil.gorestapp.di.module.ApplicationModule
import com.anil.gorestapp.di.module.NetworkModule
import com.anil.gorestapp.di.subcomponent.MainActivitySubComponent
import com.anil.gorestapp.person.injection.MainActivityBinding
import com.anil.gorestapp.person.injection.ViewModelFactoryModule
import com.anil.gorestapp.person.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ActivityModule::class,
        NetworkModule::class,
        ViewModelFactoryModule::class,
        MainActivityBinding::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
    fun mainActivitySubComponent(): MainActivitySubComponent.Factory
}