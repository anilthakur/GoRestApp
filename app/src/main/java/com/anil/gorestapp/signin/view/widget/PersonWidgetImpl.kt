package com.anil.gorestapp.signin.view.widget

import android.app.Person
import android.view.View
import javax.inject.Inject

class PersonWidgetImpl @Inject constructor() : PersonWidget{
    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun setContent(content: Person) {
        TODO("Not yet implemented")
    }

    override fun showGeneralError() {
        TODO("Not yet implemented")
    }

    override fun showNetworkError() {
        TODO("Not yet implemented")
    }

    override fun onRetry(action: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun show() {
        TODO("Not yet implemented")
    }

    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun initView(contentView: View) {
        TODO("Not yet implemented")
    }

    override val containerView: View?
        get() = TODO("Not yet implemented")
}