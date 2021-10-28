package com.anil.gorestapp.books.repository

import com.anil.gorestapp.base.base.domain.ResultData
import com.anil.gorestapp.books.entities.Books
import com.anil.gorestapp.person.entities.Person
import io.reactivex.Observable
import io.reactivex.Single

interface BooksRepo {
    fun getBooksData(): Single<Books>
}