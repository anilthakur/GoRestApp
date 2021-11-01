package com.anil.gorestapp.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.anil.gorestapp.base.domain.model.ErrorModel

 abstract class BaseViewModel  : ViewModel(), LifecycleObserver {

     sealed class State {
         data class Success(val data: Any) : State()
         object EmptyData : State()
         object Loading : State()
         object NetworkError:State()
         object GeneralError:State()

         object ENTER_PIN : State()
         object CHANGE_PIN : State()
         object CREATE_PIN:State()
         object INCORRECT_PIN:State()
     }


 }