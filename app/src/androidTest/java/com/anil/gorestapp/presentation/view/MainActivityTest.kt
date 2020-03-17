package com.anil.gorestapp.presentation.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.anil.gorestapp.R
import com.anil.gorestapp.presentation.adapter.PersonAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().getTargetContext()
        Assert.assertEquals("com.anil.gorestapp", appContext.getPackageName())
    }

    @Test
    fun mainActivityTest() {

        Thread.sleep(1500)
        val viewGroup = onView(
                allOf(withId(R.id.toolbar),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

        val textView = onView(
                allOf(withText("GoRestApp"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java),
                                                0)),
                                0),
                        isDisplayed()))
        textView.check(matches(withText("GoRestApp")))


        val textView2 = onView(
                allOf(withId(R.id.textViewAddress), withText("65697 Schamberger PlazaTurnerburgh, LA 01628"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview),
                                        0),
                                1),
                        isDisplayed()))
        textView2.check(matches(withText("65697 Schamberger PlazaTurnerburgh, LA 01628")))


        val linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recyclerView),
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                                        0)),
                        0),
                        isDisplayed()))
        linearLayout.check(matches(isDisplayed()))

    }


    @Test
    fun container_IsDisplayed() {


        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.toolbar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withText("asdasdsa")).check(doesNotExist())
    }


    @Test
    fun recyclerViewScrollToPositionTest() {
        tapRecyclerViewItem(R.id.recyclerView,15)


    }

    fun tapRecyclerViewItem(recyclerViewId: Int, position: Int) {
        onView(withId(recyclerViewId)).perform(scrollToPosition<PersonAdapter.ViewHolder>(position))
    }


    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
