package com.anil.gorestapp.person.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.di.ViewModelKey
import com.anil.gorestapp.di.qualifier.ForActivity
import com.anil.gorestapp.di.scope.PerActivity
import com.anil.gorestapp.person.view.view.MainActivity
import com.anil.gorestapp.person.viewmodel.PersonViewModel
import com.anil.gorestapp.person.viewmodel.PersonViewModelFactory
import com.anil.gorestapp.person.viewmodel.PersonViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [PersonUsecaseModule::class])
class MainActivityActivityModule {
    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: MainActivity): Context = activityInStore

    @Provides
    @IntoMap
    @ViewModelKey(PersonViewModel::class)
    fun providePersonViewModel(personViewModel: PersonViewModelImpl) : ViewModel = personViewModel
}