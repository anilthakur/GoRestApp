package com.anil.gorestapp.question.viewmodel

import androidx.lifecycle.LiveData
import com.anil.gorestapp.base.viewmodel.BaseViewModel

abstract class BooksViewModel : BaseViewModel() {
    abstract val stateLiveData: LiveData<State>
    abstract val personResponseLiveData: LiveData<State>
    abstract fun getPersonData()
}