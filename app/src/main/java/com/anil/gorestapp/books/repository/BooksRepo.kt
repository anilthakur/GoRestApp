package com.anil.gorestapp.books.repository

import com.anil.gorestapp.books.entities.Books
import io.reactivex.Single

interface BooksRepo {
    fun getBooksData(): Single<Books>
}