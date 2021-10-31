package com.anil.gorestapp.signin.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.anil.gorestapp.signin.entities.Person
import com.anil.gorestapp.signin.domain.PersonUseCase
import javax.inject.Inject

class PersonViewModelImpl @Inject constructor(private val getPersonUseCase: PersonUseCase, override val personResponseLiveData: MediatorLiveData<State>) : PersonViewModel() {


    var person: Person? = null

    init {
        personResponseLiveData.addSource(getPersonUseCase.resultLiveData()) { it?.let(::onGetPersonTypeResult) }

    }


    override fun getPersonData(isRemote:Boolean) {
        getPersonUseCase.execute(isRemote)
    }

    private fun onGetPersonTypeResult(result: PersonUseCase.Result) {
        when (result) {
            is PersonUseCase.Result.HasPersonData -> {
                person = result.person
                personResponseLiveData.value = State.Success(person!!)

            }
            is PersonUseCase.Result.Error -> {
                personResponseLiveData.value = State.NetworkError
            }
        }
    }

}