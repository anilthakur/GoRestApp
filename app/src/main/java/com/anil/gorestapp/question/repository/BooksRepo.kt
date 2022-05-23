package com.anil.gorestapp.question.repository

import com.anil.gorestapp.question.entities.Books
import io.reactivex.Single

interface BooksRepo {
    fun getBooksData(): Single<Books>
}