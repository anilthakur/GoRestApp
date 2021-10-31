package com.anil.gorestapp.applock.widget

import android.view.View
import androidx.lifecycle.LiveData
import com.anil.gorestapp.base.livedata.SingleLiveData
import com.anil.gorestapp.base.widget.ContentStateWidget

interface AppLockWidget : ContentStateWidget<View> {
    sealed class CallToAction {
        data class ValidateDigit(var digitValue: String) : CallToAction()
    }

    val onClicked: LiveData<CallToAction>
}