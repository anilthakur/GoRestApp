package com.anil.gorestapp.books.view.adapter

import android.content.Context
import android.view.View
import com.anil.gorestapp.base.adapter.BaseViewHolder
import com.anil.gorestapp.books.entities.ItemsItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_books_item.*

class BooksViewHolder(
    itemView: View,
    val context: Context
) : BaseViewHolder<ItemsItem>(itemView), LayoutContainer {

    override val containerView = itemView

    override fun bind(item: ItemsItem) {
        tv_title.text =
            "ID:->" + item.id + "Country \n " + item.saleInfo?.country + "Links\n" + item.selfLink
    }
}