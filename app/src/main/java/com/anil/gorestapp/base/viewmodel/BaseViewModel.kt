package com.anil.gorestapp.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.anil.gorestapp.base.domain.model.ErrorModel

 abstract class BaseViewModel  : ViewModel(), LifecycleObserver {

     sealed class State {
         data class Success(val data: Any) : State()
         object EmptyData : State()
         object Loading : State()
         data class Error(var errorModel: ErrorModel) : State()
     }
 }