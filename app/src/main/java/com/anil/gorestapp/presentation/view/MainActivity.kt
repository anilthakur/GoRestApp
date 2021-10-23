package com.anil.gorestapp.presentation.view

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.anil.gorestapp.R
import com.anil.gorestapp.base.network.ConnectivityReceiver
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.data.entities.Person
import com.anil.gorestapp.presentation.adapter.PersonAdapter
import com.anil.gorestapp.viewmodel.PersonViewModelImpl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.os.Build
import com.anil.gorestapp.MainApplication
import com.anil.gorestapp.person.view.widget.PersonWidget
import com.anil.gorestapp.person.view.widget.PersonWidgetImpl


class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mNetworkReceiver: BroadcastReceiver
    lateinit var personWidgetImpl: PersonWidget
    private val viewModel: PersonViewModelImpl by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PersonViewModelImpl::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this will tell to dagger, that i need dependencies
        (application as MainApplication).appComponent()?.inject(this)

        setContentView(R.layout.activity_main)
        mNetworkReceiver = ConnectivityReceiver()
        toolbar.title = getString(R.string.app_name)
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
        registerNetworkBroadcastForNougat()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        unregisterNetworkChanges()
    }

    private fun showNetworkMessage(isConnected: Boolean) {

        if (!isConnected) {
            viewModel.getPersonData(false)
        } else {
            viewModel.getPersonData(true)
        }

    }

    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    protected fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

}
