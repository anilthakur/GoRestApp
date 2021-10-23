package com.anil.gorestapp.person.injection

import androidx.lifecycle.ViewModel
import com.anil.gorestapp.person.viewmodel.PersonViewModelImpl
import dagger.Module
import dagger.Provides


@Module(includes = [PersonUsecaseModule::class])
class PersonModule {

    @Provides
    fun bindPersonViewModel(personViewModelImpl: PersonViewModelImpl): ViewModel =
        personViewModelImpl

}