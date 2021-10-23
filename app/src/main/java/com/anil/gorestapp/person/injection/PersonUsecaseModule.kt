package com.anil.gorestapp.person.injection

import androidx.lifecycle.MutableLiveData
import com.anil.gorestapp.person.domain.GetPersonUseCaseImpl
import com.anil.gorestapp.person.domain.PersonUseCase
import com.anil.gorestapp.person.repository.PersonRepo
import com.anil.gorestapp.person.repository.PersonRepoImpl
import dagger.Module
import dagger.Provides

@Module
class  PersonUsecaseModule{
    @Provides
    internal fun provideUseCase(useCase: GetPersonUseCaseImpl): PersonUseCase = useCase

    @Provides
    internal fun provideMutableLiveData() = MutableLiveData<PersonUseCase.Result>()

    @Provides
    fun providePersonRepository(personRepository: PersonRepoImpl): PersonRepo = personRepository
}