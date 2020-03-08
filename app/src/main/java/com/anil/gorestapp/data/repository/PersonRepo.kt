package com.anil.gorestapp.data.repository

import com.anil.gorestapp.base.base.domain.ResultData
import com.anil.gorestapp.data.entities.Person
import io.reactivex.Observable
import io.reactivex.Single
interface PersonRepo {

    fun getPersonData(isRemote:Boolean): Observable<ResultData>
    fun getRemoteDataObservable(person: Person? = null): Single<ResultData>
    fun getPersonDataFromDb(): Single<List<Person>>
}