package com.anil.gorestapp.data.repository

import com.anil.gorestapp.base.base.domain.ResultData
import com.anil.gorestapp.data.entities.Person
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Anil Kumar on 2020-03-07
 */
interface PersonRepo {

    fun getPersonData(): Observable<ResultData>
    fun getRemoteDataObservable(person: Person? = null): Single<ResultData>
    fun getPersonDataFromDb(): Single<List<Person>>
}