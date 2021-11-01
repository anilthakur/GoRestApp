package com.anil.gorestapp.home.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anil.gorestapp.R
import com.anil.gorestapp.home.home.HomeFragment
import com.anil.gorestapp.home.monitor.MonitorFragment
import com.anil.gorestapp.home.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.Android
import javax.inject.Inject
import javax.inject.Named


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    @Named("PerActivity1")
    lateinit var pdpListWidget: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_home)
        loadFragment(HomeFragment())
        navigation.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> fragment = HomeFragment()
            R.id.navigation_monitor -> fragment = MonitorFragment()
            R.id.navigation_profile -> fragment = ProfileFragment()
        }

        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }
}