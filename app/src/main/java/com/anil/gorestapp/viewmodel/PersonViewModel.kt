package com.anil.gorestapp.viewmodel

import androidx.lifecycle.LiveData
import com.anil.gorestapp.base.viewmodel.BaseViewModel


abstract class PersonViewModel :BaseViewModel() {
    abstract val personResponseLiveData: LiveData<State>
    abstract fun getPersonData(isRemote:Boolean)
}