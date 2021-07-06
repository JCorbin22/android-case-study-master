package com.target.targetcasestudy

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Custom matchers used to match UI elements in espresso tests.
 */
class TestViewMatchers {

    companion object {

        /**
         * Matches a view within a recycler view that matches the provided matcher.
         */
        fun childAtPosition(position: Int, parentMatcher: Matcher<View>): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("Has item at position $position")
                    parentMatcher.describeTo(description)
                }

                override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                    return parentMatcher.matches(viewHolder?.itemView)
                }
            }
        }
    }
}