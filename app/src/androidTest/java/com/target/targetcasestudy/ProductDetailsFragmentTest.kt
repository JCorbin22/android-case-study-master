package com.target.targetcasestudy

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for the ProductDetailsFragment
 */
@RunWith(AndroidJUnit4::class)
class ProductDetailsFragmentTest {

    @Before
    fun setUp() {
        launchActivity<MainActivity>()
        // This is BAD - typically you would mock the service and provide a
        // faked JSON response (or similar); doing this for time constraints
        Thread.sleep(1000)
    }

    @Test
    fun productDetailsForFirstItemIsDisplayed() {
        onView(withId(R.id.recycler_view)).check(matches(TestViewMatchers.childAtPosition(0,
            hasDescendant(allOf(withId(R.id.product_list_item_title_tv),
                withText("non mollit veniam ex"))))))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }
}
