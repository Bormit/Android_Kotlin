package ru.samsung.itacademy.mdev.twoactivitiesespressotest

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches

@RunWith(AndroidJUnit4::class)
class SecondActivityTest{
    @Rule @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    private val inputText = "test_Second"

    @Test
    @Throws(Exception::class)
    fun secondActivity(){
        onView(withId(R.id.button_main)).perform(click())

        onView(withId(R.id.text_header)).check(matches(isDisplayed()))
        onView(withId(R.id.text_message)).check(matches(isDisplayed()))
        onView(withId(R.id.button_second)).check(matches(isDisplayed()))
        onView(withId(R.id.editText_second)).check(matches(isDisplayed()))

        onView(withId(R.id.editText_second)).perform(ViewActions.typeText(inputText))
        onView(withId(R.id.button_second)).perform(click())
        onView(withId(R.id.button_main)).check(matches(isDisplayed()))

        onView(withId(R.id.text_message_reply))
            .check(matches(withText(inputText)))
    }
}
