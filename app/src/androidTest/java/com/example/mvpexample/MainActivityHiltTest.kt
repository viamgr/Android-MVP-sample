package com.example.mvpexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mvpexample.ui.main.adapter.CharactersAdapter
import com.example.mvpexample.ui.main.impl.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest

class MainActivityHiltTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureSomeViewWorkFine() {

        // Inject the dependencies to the test (if there is any @Inject field in the test)
        hiltRule.inject()

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_characters)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_characters))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharactersAdapter.ItemViewHolder>(
                    1,
                    click()
                )
            )

        onView(withId(R.id.cv_thumbnail)).check(matches(isDisplayed()))

    }
}