package com.anil.gorestapp.books.view.adapter

import com.anil.gorestapp.base.adapter.DiffCallback
import com.anil.gorestapp.books.entities.ItemsItem
import javax.inject.Inject
import javax.inject.Named

class BookAdapterImpl @Inject constructor(
    @Named("ForBookAdapter")
     diffCallback: DiffCallback,
    bookItemDelegate: BookItemDelegate
) : BookAdapter(diffCallback) {

    init {
        getDelegatesManager().addDelegate(bookItemDelegate)
    }

    override fun setBooks(books: List<ItemsItem>) {
        setItems(books)
    }
}