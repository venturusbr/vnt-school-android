package br.org.venturus.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun test_AllComponentsAreVisible() {
        onView(withId(R.id.edittext_name)).check(matches(isDisplayed()))
        onView(withId(R.id.button_done)).check(matches(isDisplayed()))
        onView(withId(R.id.textview_greeting)).check(matches(isDisplayed()))
    }

    @Test
    fun test_whenNameIsTypedGreetingThenMessageIsShown() {
        val name = "Sato"
        val assertText = context.getString(R.string.greeting_message, name)

        onView(withId(R.id.edittext_name)).perform(typeText(name))
        onView(withId(R.id.button_done)).perform(click())
        onView(withId(R.id.textview_greeting)).check(matches(withText(assertText)))
    }

    @Test
    fun test_whenNameIsEmptyThenGreetingMessageIsNotShown() {
        onView(withId(R.id.button_done)).perform(click())
        onView(withId(R.id.textview_greeting)).check(matches(withText("")))
    }

    @Test
    fun test_whenNameIsEmptySpacesThenGreetingMessageIsNotShown() {
        onView(withId(R.id.edittext_name)).perform(typeText("      "))
        onView(withId(R.id.button_done)).perform(click())
        onView(withId(R.id.textview_greeting)).check(matches(withText("")))
    }
}