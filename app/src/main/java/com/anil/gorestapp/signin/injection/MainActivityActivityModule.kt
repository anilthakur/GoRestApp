package com.anil.gorestapp.signin.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.question.injection.BooksFragmentBinding
import com.anil.gorestapp.base.di.qualifier.ForActivity
import com.anil.gorestapp.base.di.scope.PerActivity
import com.anil.gorestapp.signin.domain.GetPersonUseCaseImpl
import com.anil.gorestapp.signin.domain.PersonUseCase
import com.anil.gorestapp.signin.repository.PersonRepo
import com.anil.gorestapp.signin.repository.PersonRepoImpl
import com.anil.gorestapp.signin.view.view.MainActivity
import com.anil.gorestapp.signin.view.widget.PersonWidget
import com.anil.gorestapp.signin.view.widget.PersonWidgetImpl
import com.anil.gorestapp.signin.viewmodel.PersonViewModel
import com.anil.gorestapp.signin.viewmodel.PersonViewModelFactory
import com.anil.gorestapp.signin.viewmodel.PersonViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module(includes = [BooksFragmentBinding::class])
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

    @PerActivity
    @Provides
    @Named("PerActivity")
    open fun provideInt(): String {
        return "hello"
    }

}