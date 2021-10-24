package com.anil.gorestapp.person.injection

import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.person.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactoryModule(factory: ViewModelFactory): ViewModelProvider.Factory
}