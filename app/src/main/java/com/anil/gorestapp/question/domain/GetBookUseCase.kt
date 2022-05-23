package com.anil.gorestapp.question.domain

import com.anil.gorestapp.base.base.domain.UseCase
import com.anil.gorestapp.question.entities.Books

interface GetBookUseCase : UseCase {

    interface Callback {

        fun onBooksLoaded(books: Books)

        fun onBooksLoadingError(throwable: Throwable)

    }

    fun setCallback(callback: Callback)
    fun execute()
}