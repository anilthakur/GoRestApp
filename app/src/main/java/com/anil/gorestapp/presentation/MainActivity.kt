package com.anil.gorestapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.anil.gorestapp.R
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.viewmodel.PersonViewModelImpl
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: PersonViewModelImpl by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PersonViewModelImpl::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewModel.getPersonData()
        offerTypeResponseMutableData()

    }

    private fun offerTypeResponseMutableData() {
        viewModel.personResponseLiveData.observe(this, Observer { state ->
            if (state != null) {
                if (state is BaseViewModel.State.Success) {
                    val personData = (state as BaseViewModel.State.Success).data as Person

                    Log.d("Response", "Data" + personData.result?.size)

                } else if (state is BaseViewModel.State.Error) {
                    Log.d("Response", "Error")
                }
            }
        })
    }

}
