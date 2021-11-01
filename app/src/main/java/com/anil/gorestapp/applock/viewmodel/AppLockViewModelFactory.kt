package com.anil.gorestapp.applock.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.gorestapp.applock.viewmodel.AppLockViewModel.*
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.preferences.AppPreference
import javax.inject.Inject

class AppLockViewModelFactory @Inject constructor(
    private val pinStateLiveData: MediatorLiveData<BaseViewModel.State>,
    private  val apppPreference: AppPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return AppLockViewModelmpl(pinStateLiveData, apppPreference) as T as T
    }
}