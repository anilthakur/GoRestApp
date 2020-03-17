package com.anil.gorestapp.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.domain.PersonUseCase
import com.anil.gorestapp.utils.properties.AppProperties
import javax.inject.Inject

class PersonViewModelImpl @Inject constructor(private val getPersonUseCase: PersonUseCase, override val personResponseLiveData: MediatorLiveData<State>,val appProperties: AppProperties) : PersonViewModel() {


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
                appProperties.selectedClubcardNumber.set("19823831923")
                appProperties.currentOrderByCategory.set(123)
                appProperties.shouldShowNewAboutClubcardPointsVarient.set(false)

                Log.d("CLUBCARDNUMBEER",appProperties.selectedClubcardNumber.get()+""+appProperties.currentOrderByCategory.get()+""+appProperties.shouldShowNewAboutClubcardPointsVarient.get());

            }
            is PersonUseCase.Result.Error -> {
                personResponseLiveData.value = State.Error(result.errorModel)
            }
        }
    }

}