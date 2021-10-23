package com.anil.gorestapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.anil.gorestapp.data.entities.Links
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.data.entities.ResultItem
import com.anil.gorestapp.domain.PersonUseCase
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
                person = getFakeData()
                personResponseLiveData.value = State.Success(person!!)

            }
            is PersonUseCase.Result.Error -> {
                personResponseLiveData.value = State.Error(result.errorModel)
            }
        }
    }

    // Todo: this is just for testing purpose
    private fun getFakeData() = Person("1234", result = arrayListOf(
        ResultItem(
            "www.google.com",
            "Some Address",
            "M",
            "1233456",
            Links(),
            "01/01/2019",
            "Last",
            "someTestId",
            "First",
            "test@gmail.com",
            "single"
        )
    ))
}