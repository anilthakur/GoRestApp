package com.anil.gorestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.anil.gorestapp.R
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.presentation.adapter.PersonAdapter
import com.anil.gorestapp.viewmodel.PersonViewModelImpl
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val adapter: PagerAdapter? = null
    private val viewModel: PersonViewModelImpl by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PersonViewModelImpl::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar);
        viewModel.getPersonData()
        offerTypeResponseMutableData()

    }

    private fun offerTypeResponseMutableData() {
        viewModel.personResponseLiveData.observe(this, Observer { state ->
            if (state != null) {
                if (state is BaseViewModel.State.Success) {
                    val personData = (state as BaseViewModel.State.Success).data as Person
                    recyclerView.visibility = View.VISIBLE
                    no_dataFound.visibility = View.GONE

                    val adapter = PersonAdapter(personData.result)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager =
                            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

                } else if (state is BaseViewModel.State.Error) {
                    recyclerView.visibility = View.GONE
                    no_dataFound.visibility = View.VISIBLE
                }
            }
        })
    }

}
