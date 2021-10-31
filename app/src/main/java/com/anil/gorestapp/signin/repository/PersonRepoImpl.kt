package com.anil.gorestapp.signin.repository

import com.anil.gorestapp.base.base.domain.ResultData
import com.anil.gorestapp.base.base.schedulers.BaseSchedulerProvider
import com.anil.gorestapp.base.base.schedulers.TrampolineSchedulerProvider
import com.anil.gorestapp.base.domain.ResponseServiceCode
import com.anil.gorestapp.base.domain.model.ErrorModel
import com.anil.gorestapp.signin.local.PersonDao
import com.anil.gorestapp.signin.entities.Person
import com.anil.gorestapp.base.dataservice.remote.RetrofitService
import com.anil.gorestapp.utils.Constant
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import retrofit2.HttpException
import javax.inject.Inject


class PersonRepoImpl @Inject constructor(val api: RetrofitService, private val personDao: PersonDao, private val schedulerProvider: BaseSchedulerProvider, private val isTestMode: Boolean = false) : PersonRepo {
    override fun getPersonData(isRemote:Boolean): Observable<ResultData> {
        return getPersonDataFromDb()
                .toObservable()
                .subscribeOn(schedulerProvider.io())
                .flatMap { personList ->
                    if (personList.isNullOrEmpty()) {
                        return@flatMap getRemoteDataObservable().toObservable()
                    } else {
                        val personLocalData = personList[0]
                        if (isTestMode) {
                            return@flatMap validateAndFetchData(isRemote,personLocalData)
                        } else {
                            if (isRemote) {
                                return@flatMap validateAndFetchData(isRemote,personLocalData)
                            } else {
                                return@flatMap Observable.just(ResultData.fromData(personLocalData))
                            }
                        }
                    }

                }
    }


    fun validateAndFetchData(isRemote: Boolean,person: Person?): Observable<ResultData> {
        return if (isRemote||person == null) {
            getRemoteDataObservable(person).toObservable()
        } else {
            Observable.just(ResultData.fromData(person))
        }
    }


    override fun getRemoteDataObservable(person: Person?): Single<ResultData> {


        return api.getGetPerson(Constant.DATA_TYPE, Constant.TOKEN)
                .subscribeOn(schedulerProvider.io())
                .flatMap { personData ->
                    personData.let {
                        it.id = Person.INSTANCE_ID
                        savePersonData(it)
                    }
                    return@flatMap Single.just(ResultData.fromData(personData))
                }
                .onErrorResumeNext { error ->
                    val errorModel: ErrorModel = if (error is HttpException) {
                        ErrorModel(key = Person.INSTANCE_ID, code = error.code(), message = error.message(), responseCode = ResponseServiceCode.OK_INSTANCE)
                    } else {
                        ErrorModel(key = Person.INSTANCE_ID, message = error.message
                                ?: "", isServiceError = true)
                    }

                    return@onErrorResumeNext Single.just(ResultData.fromError(null, errorModel))
                }


    }

    override fun getPersonDataFromDb(): Single<List<Person>> {
        return personDao.getPerson()
    }


    private fun savePersonData(person: Person?) {
        if (schedulerProvider is TrampolineSchedulerProvider) {
            personDao.upsertPerson(person!!)
        } else {
            Observable.create { subscriber: ObservableEmitter<Any?> ->
                personDao.upsertPerson(person!!)
                subscriber.onComplete()
            }.subscribeOn(schedulerProvider.computation()).subscribe()
        }

    }

}