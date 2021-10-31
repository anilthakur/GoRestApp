package com.anil.gorestapp.applock.injection

import com.anil.gorestapp.applock.view.AppLockActivity
import com.anil.gorestapp.base.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppLockActivityBinding {
    @PerActivity
    @ContributesAndroidInjector(modules = [AppLockActivityModule::class])
    abstract fun bindAppLockBinding(): AppLockActivity
}