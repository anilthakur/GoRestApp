package com.anil.gorestapp.person.view.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anil.gorestapp.R
import com.anil.gorestapp.base.MainApplication
import com.anil.gorestapp.base.viewmodel.BaseViewModel
import com.anil.gorestapp.person.entities.Person
import com.anil.gorestapp.person.view.adapter.PersonAdapter
import com.anil.gorestapp.person.viewmodel.PersonViewModel
import com.anil.gorestapp.person.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PersonViewModel by viewModels { viewModelFactory }
    private val adapter = PersonAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication)
            .appComponent()
            .mainActivitySubComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        offerTypeResponseMutableData()
        viewModel.getPersonData(true)
        recyclerView.adapter = adapter
    }

    private fun offerTypeResponseMutableData() {
        viewModel.personResponseLiveData.observe(viewLifecycleOwner, { state ->
            if (state != null) {
                if (state is BaseViewModel.State.Success) {
                    val personData = state.data as Person
                    adapter.setUserList(personData.result)
                }
            }
        })
    }
}