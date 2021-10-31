package com.anil.gorestapp.signin.view.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anil.gorestapp.R
import com.anil.gorestapp.signin.view.widget.PersonWidget
import com.anil.gorestapp.signin.viewmodel.PersonViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named


class MainActivity : AppCompatActivity(){

    @Inject
    lateinit var viewModel: PersonViewModel

    @Inject
    lateinit var personWidgetImpl: PersonWidget

    @Inject
    @Named("PerActivity")
    lateinit var pdpListWidget: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.app_name)
    }


}
