package com.anil.gorestapp.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.anil.gorestapp.R
import com.google.android.material.tabs.TabLayout

class WelcomeActivity : AppCompatActivity() {
    private lateinit var page: ViewPager
    private val imagerArray = arrayListOf<Int>()
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        page = findViewById(R.id.my_pager)
        tabLayout = findViewById(R.id.my_tablayout)
        imagerArray.add(R.drawable.item1)
        imagerArray.add(R.drawable.item2)
        imagerArray.add(R.drawable.item4)
        val pagerAdapter = SlidingImageAdapter(this, imagerArray)
        page.adapter = pagerAdapter
        page.currentItem = 0
        tabLayout.setupWithViewPager(page, true)
    }
}