package com.anil.gorestapp.di.module

import androidx.lifecycle.MutableLiveData
import com.anil.gorestapp.domain.GetPersonUseCaseImpl
import com.anil.gorestapp.domain.PersonUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by Anil Kumar on 2020-03-08
 */
@Module
class  PersonUsecaseModule{
    @Provides
    internal fun provideUseCase(useCase: GetPersonUseCaseImpl): PersonUseCase = useCase

    @Provides
    internal fun provideMutableLiveData() = MutableLiveData<PersonUseCase.Result>()


}