package com.anil.gorestapp.di.module

import androidx.lifecycle.MutableLiveData
import com.anil.gorestapp.person.domain.GetPersonUseCaseImpl
import com.anil.gorestapp.person.domain.PersonUseCase
import dagger.Module
import dagger.Provides

@Module
class  PersonUsecaseModule{
    @Provides
    internal fun provideUseCase(useCase: GetPersonUseCaseImpl): PersonUseCase = useCase

    @Provides
    internal fun provideMutableLiveData() = MutableLiveData<PersonUseCase.Result>()
}