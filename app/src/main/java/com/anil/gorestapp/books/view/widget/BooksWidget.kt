package com.anil.gorestapp.books.view.widget

import com.anil.gorestapp.base.widget.ContentStateWidget
import com.anil.gorestapp.books.entities.Books

interface BooksWidget : ContentStateWidget<Books> {

    enum class State {
        LOADING,
        CONTENT,
        GENERAL_ERROR,
        NETWORK_ERROR
    }
}