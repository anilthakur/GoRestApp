package com.anil.gorestapp.books.view.widget

import android.view.View
import com.anil.gorestapp.books.entities.Books
import com.anil.gorestapp.books.view.widget.BooksWidget.*
import kotlinx.android.synthetic.main.view_books_widget.*
import javax.inject.Inject

class BooksWidgetImpl @Inject constructor() : BooksWidget {
    override lateinit var containerView: View

    override fun initView(contentView: View) {
        this.containerView = contentView
    }


    override fun showLoading() = showState(State.LOADING)


    override fun setContent(content: Books) {
        showState(State.CONTENT)
    }

    override fun showGeneralError() = showState(State.GENERAL_ERROR)

    override fun showNetworkError() = showState(State.NETWORK_ERROR)

    override fun onRetry(action: () -> Unit) {
    }

    override fun show() {
        books_view_flipper.visibility = View.VISIBLE
    }

    override fun hide() {
        books_view_flipper.visibility = View.GONE
    }

    private fun showLoading(isShow: Boolean) {

    }

    private fun showState(state: State) {
        if (books_view_flipper.displayedChild != state.ordinal) {
            books_view_flipper.displayedChild = state.ordinal
        }
    }

}