package com.anil.gorestapp.person.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.person.domain.PersonUseCase
import javax.inject.Inject

class PersonViewModelFactory
@Inject constructor(private val getPersonUseCase: PersonUseCase, private val personTypeResponseLiveData: MediatorLiveData<BaseViewModel.State>
) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(p0: Class<T>): T {
            return  PersonViewModelImpl(getPersonUseCase, personTypeResponseLiveData) as T as T

    }


}
