package com.anil.gorestapp.person.view.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anil.gorestapp.R
import com.anil.gorestapp.base.network.ConnectivityReceiver
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.books.view.BooksFragment
import com.anil.gorestapp.books.viewmodel.BooksViewModel
import com.anil.gorestapp.person.entities.Person
import com.anil.gorestapp.person.view.adapter.PersonAdapter
import com.anil.gorestapp.person.view.widget.PersonWidget
import com.anil.gorestapp.person.viewmodel.PersonViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named


class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

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
//        showNetworkMessage(true)
//        setSupportActionBar(toolbar);
//        offerTypeResponseMutableData()
        showFragment()
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

                } else if (state is BaseViewModel.State.NetworkError) {
                    recyclerView.visibility = View.GONE
                    no_dataFound.visibility = View.VISIBLE
                }
            }
        })
    }



    private fun showNetworkMessage(isConnected: Boolean) {

        if (!isConnected) {
            viewModel.getPersonData(false)
        } else {
            viewModel.getPersonData(true)
        }

    }

    fun showFragment(){
        supportFragmentManager
            // 3
            .beginTransaction()
            // 4
            .add(R.id.fragment_main, BooksFragment.newInstance(), "books_fragment")
            // 5
            .commit()
    }


}
