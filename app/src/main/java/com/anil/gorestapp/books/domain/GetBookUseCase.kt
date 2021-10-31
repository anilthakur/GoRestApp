package com.anil.gorestapp.books.domain

import com.anil.gorestapp.base.base.domain.UseCase
import com.anil.gorestapp.books.entities.Books

interface GetBookUseCase : UseCase {

    interface Callback {

        fun onBooksLoaded(books: Books)

        fun onBooksLoadingError(throwable: Throwable)

    }

    fun setCallback(callback: Callback)
    fun execute()
}