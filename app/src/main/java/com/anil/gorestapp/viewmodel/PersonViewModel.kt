package com.anil.gorestapp.viewmodel

import androidx.lifecycle.LiveData
import com.anil.gorestapp.base.viewmodel.BaseViewModel

/**
 * Created by Anil Kumar on 2020-03-08
 */
abstract class PersonViewModel :BaseViewModel() {
    abstract val personResponseLiveData: LiveData<State>
    abstract fun getPersonData()
}