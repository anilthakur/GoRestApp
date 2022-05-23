package com.anil.gorestapp.base.dataservice.remote

import com.anil.gorestapp.question.entities.Books
import com.anil.gorestapp.signin.entities.Person
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
interface RetrofitService {

    @GET("public-api/users")
    fun getGetPerson( @Query("_format") format: String,@Query("access-token") accessToke:String): Single<Person>

    @GET("books/v1/volumes")
    fun getBooks( @Query("q") format: String,@Query("inauthor") accessToke:String): Single<Books>
}