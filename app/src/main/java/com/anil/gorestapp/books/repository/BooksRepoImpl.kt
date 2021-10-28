package com.anil.gorestapp.books.repository

import com.anil.gorestapp.books.entities.Books
import com.anil.gorestapp.base.dataservice.remote.RetrofitService
import io.reactivex.Single
import javax.inject.Inject

class BooksRepoImpl @Inject constructor(
    val api: RetrofitService
) : BooksRepo {

    override fun getBooksData(): Single<Books> {

        return api.getBooks("harry", "rowling")

    }
}