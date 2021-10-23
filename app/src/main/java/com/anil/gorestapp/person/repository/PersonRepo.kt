package com.anil.gorestapp.person.repository

import com.anil.gorestapp.base.base.domain.ResultData
import com.anil.gorestapp.person.entities.Person
import io.reactivex.Observable
import io.reactivex.Single
interface PersonRepo {

    fun getPersonData(isRemote:Boolean): Observable<ResultData>
    fun getRemoteDataObservable(person: Person? = null): Single<ResultData>
    fun getPersonDataFromDb(): Single<List<Person>>
}