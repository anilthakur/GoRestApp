package com.anil.gorestapp.home.injection

import com.anil.gorestapp.base.di.scope.PerActivity
import com.anil.gorestapp.home.view.HomeActivity
import com.anil.gorestapp.signin.injection.MainActivityActivityModule
import com.anil.gorestapp.signin.view.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityBinding {

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindMainActivityBinding(): HomeActivity

}