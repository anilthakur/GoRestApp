package com.anil.gorestapp.person.injection

import com.anil.gorestapp.base.di.scope.PerActivity
import com.anil.gorestapp.person.view.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBinding {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityActivityModule::class])
    abstract fun bindMainActivityBinding(): MainActivity


}