package com.anil.gorestapp.question.view.adapter

import com.anil.gorestapp.base.adapter.DiffCallback
import com.anil.gorestapp.base.adapter.DisplayableAdapter
import com.anil.gorestapp.question.entities.ItemsItem

abstract class BookAdapter(diffCallback: DiffCallback) : DisplayableAdapter(diffCallback) {
    abstract fun setBooks(books: List<ItemsItem>)
}