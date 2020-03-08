package com.anil.gorestapp.domain

import androidx.lifecycle.LiveData
import com.anil.gorestapp.base.domain.model.ErrorModel
import com.anil.gorestapp.data.entities.Person

interface PersonUseCase {
    sealed class Result {
        data class HasPersonData(var person: Person) : Result()
        data class Error(val errorModel: ErrorModel) : Result()
    }

    fun resultLiveData(): LiveData<Result>
    fun execute(isRemote:Boolean)
}