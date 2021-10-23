package com.anil.gorestapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.person.viewmodel.PersonViewModelFactory
import com.anil.gorestapp.person.viewmodel.PersonViewModelImpl
import com.tesco.clubcardmobile.features.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class PersonModule {





    @Binds
    @IntoMap
    @ViewModelKey(PersonViewModelImpl::class)
    abstract fun bindPersonViewModel(personViewModelImpl: PersonViewModelImpl): ViewModel
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: PersonViewModelFactory):  ViewModelProvider.Factory
}