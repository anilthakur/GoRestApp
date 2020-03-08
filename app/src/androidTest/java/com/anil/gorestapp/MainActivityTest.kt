package com.anil.gorestapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.anil.gorestapp.presentation.view.MainActivity
import junit.framework.Assert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(
        AndroidJUnit4ClassRunner::class
)
class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().getTargetContext()
        Assert.assertEquals("com.anil.gorestapp", appContext.getPackageName())
    }


    @Test
    fun container_IsDisplayed() {

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }




    @Test
    fun recyclerViewScrollToPositionTest() {
        val recyclerView = activityRule.getActivity().findViewById<RecyclerView>(R.id.recyclerView)
        val itemCount = recyclerView.getAdapter()!!.getItemCount()
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount))
    }
}