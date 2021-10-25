package com.anil.gorestapp.person.view.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anil.gorestapp.R
import com.anil.gorestapp.base.network.ConnectivityReceiver
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.person.entities.Person
import com.anil.gorestapp.person.view.adapter.PersonAdapter
import com.anil.gorestapp.person.view.widget.PersonWidget
import com.anil.gorestapp.person.viewmodel.PersonViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    @Inject
    lateinit var viewModel: PersonViewModel

    @Inject
    lateinit var personWidgetImpl: PersonWidget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.app_name)
        showNetworkMessage(true)
        setSupportActionBar(toolbar);
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

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun showNetworkMessage(isConnected: Boolean) {

        if (!isConnected) {
            viewModel.getPersonData(false)
        } else {
            viewModel.getPersonData(true)
        }

    }


}
