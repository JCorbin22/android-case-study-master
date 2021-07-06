package com.target.targetcasestudy

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for the ProductListFragment
 */
@RunWith(AndroidJUnit4::class)
class ProuctListFragmentTest {

//  In another world, I would have mocked the ViewModel and LiveData in order to fake the
//  JSON response from the service, and/or use an idling resource instead of Thread.sleeps

  @Before
  fun setUp() {
    launchActivity<MainActivity>()
      // This is BAD - typically you would mock the service and provide a
      // faked JSON response (or similar); doing this for time constraints
      Thread.sleep(1000)
  }

  @Test
  fun productListFirstItemIsDisplayed() {
      onView(withId(R.id.recycler_view)).check(matches(TestViewMatchers.childAtPosition(0,
          hasDescendant(allOf(withId(R.id.product_list_item_title_tv),
              withText("non mollit veniam ex"))))))
  }

    @Test
    fun productListLastItemIsDisplayed() {
        onView(withId(R.id.recycler_view)).perform(
          RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(24))
        onView(withId(R.id.recycler_view)).check(
          matches(TestViewMatchers.childAtPosition(24,
            hasDescendant(allOf(withId(R.id.product_list_item_title_tv),
          withText("In tempor metus busterus parcelableus"))))))
    }
}
