package com.anil.gorestapp.question.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.question.domain.GetBookUseCase
import javax.inject.Inject

class BooksViewModelFactory @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val personTypeResponseLiveData: MediatorLiveData<BaseViewModel.State>,
    private val stateLiveData: MutableLiveData<BaseViewModel.State>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return BooksViewModelImpl(getBookUseCase, personTypeResponseLiveData,stateLiveData) as T as T

    }


}