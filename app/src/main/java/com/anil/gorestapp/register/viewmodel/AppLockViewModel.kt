package com.anil.gorestapp.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anil.gorestapp.base.viewmodel.BaseViewModel

abstract class AppLockViewModel :ViewModel() {
    abstract fun saveAppLockPin(digitValue: String)
    abstract fun validatePin(digitValue: String)
    abstract fun changePassword(oldPassword: String, newpassword: String)

    abstract val pinStateLiveData: LiveData<BaseViewModel.State>
}