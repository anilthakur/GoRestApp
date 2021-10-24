package com.anil.gorestapp.person.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.anil.gorestapp.person.entities.Person
import com.anil.gorestapp.person.domain.PersonUseCase
import com.anil.gorestapp.person.entities.Links
import com.anil.gorestapp.person.entities.ResultItem
import javax.inject.Inject

class PersonViewModelImpl
@Inject constructor(
    private val getPersonUseCase: PersonUseCase,
    override val personResponseLiveData: MediatorLiveData<State>
) : PersonViewModel() {

    var person: Person? = null

    init {
        personResponseLiveData.addSource(getPersonUseCase.resultLiveData()) { it?.let(::onGetPersonTypeResult) }
    }

    override fun getPersonData(isRemote: Boolean) {
        getPersonUseCase.execute(isRemote)
    }

    private fun onGetPersonTypeResult(result: PersonUseCase.Result) {
        when (result) {
            is PersonUseCase.Result.HasPersonData -> {
                person = getFakeData() //result.person
                personResponseLiveData.value = State.Success(person!!)
            }
            is PersonUseCase.Result.Error -> {
                personResponseLiveData.value = State.Error(result.errorModel)
            }
        }
    }

    // Todo: this is just for testing purpose
    private fun getFakeData() = Person(
        "1234", result = arrayListOf(
            ResultItem(
                "www.google.com",
                "Gwalior",
                "M",
                "1233456",
                Links(),
                "01/01/2019",
                "Mahore",
                "someTestId",
                "Parmesh",
                "test@gmail.com",
                "single"
            ),
            ResultItem(
                "www.google.com",
                "Bangalore",
                "M",
                "1233456",
                Links(),
                "01/01/2019",
                "Rai",
                "someTestId",
                "Ramesh",
                "test@gmail.com",
                "single"
            )
        )
    )

}