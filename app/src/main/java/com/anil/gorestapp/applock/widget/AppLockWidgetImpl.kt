package com.anil.gorestapp.applock.widget

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import com.anil.gorestapp.base.livedata.SingleLiveData
import kotlinx.android.synthetic.main.view_app_lock_registraion.*
import javax.inject.Inject


class AppLockWidgetImpl @Inject constructor(
    private val context: Context,
    override val onClicked: SingleLiveData<AppLockWidget.CallToAction>
) : AppLockWidget {
    override lateinit var containerView: View

    override fun setContent(content: View) {

    }

    override fun initView(contentView: View) {
        this.containerView = contentView
        val edit = ArrayList<EditText>()
        edit.add(otp_edit_box1)
        edit.add(otp_edit_box2)
        edit.add(otp_edit_box3)
        edit.add(otp_edit_box4)
        otp_edit_box1.addTextChangedListener(GenericTextWatcher(otp_edit_box1, edit))
        otp_edit_box2.addTextChangedListener(GenericTextWatcher(otp_edit_box2, edit))
        otp_edit_box3.addTextChangedListener(GenericTextWatcher(otp_edit_box3, edit))
        otp_edit_box4.addTextChangedListener(GenericTextWatcher(otp_edit_box4, edit))

        verify_otp_btn.setOnClickListener { _ ->
            var pin =
                "${otp_edit_box1.text}${otp_edit_box2.text}${otp_edit_box3.text}${otp_edit_box4.text}"
            Log.d("PINVALUE", pin)
            if (pin.length == 4) {
                onClicked.value = AppLockWidget.CallToAction.ValidateDigit(pin)
            }

        }

    }

    override fun show() {
    }

    override fun hide() {
    }

    override fun showLoading() {
    }

    override fun showGeneralError() {
    }

    override fun showNetworkError() {
    }

    override fun onRetry(action: () -> Unit) {
    }




}