package com.anil.gorestapp.home.monitor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anil.gorestapp.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Named


class MonitorFragment : Fragment() {

    @Inject
    @Named("PerFragment")
    lateinit var pdpListWidget: String
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Dataaaaa","Value"+pdpListWidget)

        return inflater.inflate(R.layout.fragment_books, container, false)
    }
}