package com.anil.gorestapp.base.di.module

import com.anil.gorestapp.preferences.AppPreference
import com.anil.gorestapp.preferences.AppPreferenceImpl
import dagger.Binds
import dagger.Module

@Module
abstract  class PersistanceModule {

    @Binds
    abstract fun bindSharedPreferences(appPreferenceImpl: AppPreferenceImpl): AppPreference
}