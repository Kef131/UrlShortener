package com.nubank.urlshortener.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nubank.urlshortener.R
import com.nubank.urlshortener.data.model.Alias
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivity_displayedElements() {
        onView(withId(R.id.tv_titleApp)).check(matches(isDisplayed()))
        onView(withId(R.id.et_inputUrl)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_sendUrl)).check(matches(isDisplayed()))
        onView(withId(R.id.divider)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tittleListUrl)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_listAliases)).check(matches(isDisplayed()))
    }
    @Test
    fun sendUrlClick_updatesAliasList() {

        val alias = Alias(
            alias = "https://example.com",
            links = Alias.Links(
                self = "https://example.com",
                short = "https://example.com/alias/123456789"
            )
        )
        onView(withId(R.id.et_inputUrl)).perform(typeText("https://example.com"), closeSoftKeyboard())
        onView(withId(R.id.btn_sendUrl)).perform(click())


        // Check if the text view has been updated with the expected text
        onView(withId(R.id.rv_listAliases))
            .perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(alias.links!!.short))))
    }

}