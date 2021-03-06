package com.anil.gorestapp.data.remote

import com.anil.gorestapp.data.entities.Person
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
interface RetrofitService {

    @GET("public-api/users")
    fun getGetPerson( @Query("_format") format: String,@Query("access-token") accessToke:String): Single<Person>
}