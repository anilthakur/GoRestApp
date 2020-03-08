package com.anil.gorestapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.domain.PersonUseCase
import javax.inject.Inject

/**
 * Created by Anil Kumar on 2020-03-07
 */
class PersonViewModelImpl @Inject constructor(private val getPersonUseCase: PersonUseCase, override val personResponseLiveData: MediatorLiveData<State>) : PersonViewModel() {


    var person: Person? = null

    init {
        personResponseLiveData.addSource(getPersonUseCase.resultLiveData()) { it?.let(::onGetPersonTypeResult) }

    }


    override fun getPersonData() {
        getPersonUseCase.execute()
    }

    private fun onGetPersonTypeResult(result: PersonUseCase.Result) {
        when (result) {
            is PersonUseCase.Result.HasPersonData -> {
                person = result.person
                personResponseLiveData.value = State.Success(person!!)

            }
            is PersonUseCase.Result.Error -> {
                personResponseLiveData.value = State.Error(result.errorModel)
            }
        }
    }

}