package com.anil.gorestapp.person.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.di.qualifier.ForActivity
import com.anil.gorestapp.di.scope.PerActivity
import com.anil.gorestapp.person.domain.GetPersonUseCaseImpl
import com.anil.gorestapp.person.domain.PersonUseCase
import com.anil.gorestapp.person.repository.PersonRepo
import com.anil.gorestapp.person.repository.PersonRepoImpl
import com.anil.gorestapp.person.view.view.MainActivity
import com.anil.gorestapp.person.view.widget.PersonWidget
import com.anil.gorestapp.person.view.widget.PersonWidgetImpl
import com.anil.gorestapp.person.viewmodel.PersonViewModel
import com.anil.gorestapp.person.viewmodel.PersonViewModelFactory
import com.anil.gorestapp.person.viewmodel.PersonViewModelImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityActivityModule {
    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: MainActivity): Context = activityInStore


    @Provides
    @PerActivity
    fun providePersonViewModel(
        mainActivity: MainActivity,
        factory: PersonViewModelFactory
    ): PersonViewModel =
        ViewModelProvider(mainActivity, factory).get(PersonViewModel::class.java)

    @Provides
    @PerActivity
    fun bindPersonViewModel(personViewModelImpl: PersonViewModelImpl): ViewModel =
        personViewModelImpl

    @Provides
    @PerActivity
    fun providePersonWidget(personWidget: PersonWidgetImpl): PersonWidget = personWidget

    @Provides
    internal fun provideUseCase(useCase: GetPersonUseCaseImpl): PersonUseCase = useCase

    @Provides
    internal fun provideMutableLiveData() = MutableLiveData<PersonUseCase.Result>()

    @Provides
    fun providePersonRepository(personRepository: PersonRepoImpl): PersonRepo = personRepository

}