package com.anil.gorestapp.di.component

import android.app.Application
import com.android.artgallery.di.module.ActivityModule
import com.anil.gorestapp.MainApplication
import com.anil.gorestapp.di.module.ApplicationModule
import com.anil.gorestapp.di.module.NetworkModule
import com.anil.gorestapp.di.module.PersonUsecaseModule
import com.anil.gorestapp.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Anil Kumar on 2020-03-07
 */

@Singleton
@Component(
        modules = [

            ApplicationModule::class,
            ActivityModule::class,
            NetworkModule::class,
            PersonUsecaseModule::class
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
    fun inject(mainActivity: MainActivity)
}