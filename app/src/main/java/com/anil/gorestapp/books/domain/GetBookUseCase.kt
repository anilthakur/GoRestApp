package com.anil.gorestapp.books.domain

import androidx.lifecycle.LiveData
import com.anil.gorestapp.base.base.domain.UseCase
import com.anil.gorestapp.base.domain.model.ErrorModel
import com.anil.gorestapp.books.entities.Books
import com.anil.gorestapp.person.entities.Person

interface GetBookUseCase : UseCase {

    interface Callback {


        fun onBooksLoaded(books: Books)

        fun onBooksLoadingError(throwable: Throwable)

    }

    fun setCallback(callback: Callback)
    fun execute()
}