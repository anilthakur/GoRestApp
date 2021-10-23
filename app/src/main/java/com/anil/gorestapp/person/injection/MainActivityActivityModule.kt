package com.anil.gorestapp.person.injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.di.qualifier.ForActivity
import com.anil.gorestapp.di.scope.PerActivity
import com.anil.gorestapp.person.view.view.MainActivity
import com.anil.gorestapp.person.viewmodel.PersonViewModel
import com.anil.gorestapp.person.viewmodel.PersonViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [PersonUsecaseModule::class])
class MainActivityActivityModule {
    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: MainActivity): Context = activityInStore

    @Provides
    @PerActivity
    @ForActivity
    fun providePersonViewModel(
        mainActivity: MainActivity,
        factory: PersonViewModelFactory
    ): PersonViewModel =
        ViewModelProvider(mainActivity, factory).get(PersonViewModel::class.java)
}