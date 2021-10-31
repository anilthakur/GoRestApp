package com.anil.gorestapp.base.di.component

import android.app.Application
import com.anil.gorestapp.applock.injection.AppLockActivityBinding
import com.anil.gorestapp.base.MainApplication
import com.anil.gorestapp.books.injection.BooksFragmentBinding
import com.anil.gorestapp.base.di.module.ApplicationModule
import com.anil.gorestapp.base.di.module.NetworkModule
import com.anil.gorestapp.base.di.module.PersistanceModule
import com.anil.gorestapp.base.di.scope.PerApplication
import com.anil.gorestapp.signin.injection.MainActivityBinding
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@PerApplication
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        MainActivityBinding::class,
        AppLockActivityBinding::class,
        BooksFragmentBinding::class,
        PersistanceModule::class,
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