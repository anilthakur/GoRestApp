package com.anil.gorestapp.person.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anil.gorestapp.base.base.domain.ResultData
import com.anil.gorestapp.base.base.schedulers.BaseSchedulerProvider
import com.anil.gorestapp.base.domain.BaseUseCase
import com.anil.gorestapp.person.entities.Person
import com.anil.gorestapp.person.repository.PersonRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetPersonUseCaseImpl @Inject constructor(
        compositeDisposable: CompositeDisposable,
        private val getPersonLiveData: MutableLiveData<PersonUseCase.Result>,
        private val personRepo: PersonRepo,
        private val schedulerProvider: BaseSchedulerProvider
) : BaseUseCase(compositeDisposable), PersonUseCase {


    override fun resultLiveData(): LiveData<PersonUseCase.Result> = getPersonLiveData

    override fun execute(isRemote:Boolean) {
        trackDisposable(personRepo.getPersonData(isRemote)
                .observeOn(schedulerProvider.ui())
                .subscribe { resultData ->
                    if (resultData.isSuccess()) {
                        handleSuccess(resultData)
                    } else if (resultData.isError()) {
                        resultData.data?.let {
                            handleSuccess(resultData)
                        } ?: kotlin.run {
                            getPersonLiveData.value = PersonUseCase.Result.Error(resultData.errorModel!!)
                        }
                    }
                })
    }

    private fun handleSuccess(resultData: ResultData) {
        getPersonLiveData.value = PersonUseCase.Result.HasPersonData(resultData.data as Person)
    }
}