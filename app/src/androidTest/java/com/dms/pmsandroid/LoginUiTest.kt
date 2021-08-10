package com.dms.pmsandroid

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dms.pmsandroid.feature.calendar.ui.CalendarFragment
import com.dms.pmsandroid.feature.login.ui.fragment.LoginFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginUiTest {
    @Test
    fun testLoginFragment(){
        val fragmentArgs = Bundle().apply {
            putInt("selectedListItem", 0)
        }
        val factory = FragmentFactory()
        val scenario = launchFragmentInContainer<CalendarFragment>(fragmentArgs,factory = factory)
        onView(withId(R.id.calendar_event_tv)).check(matches(withText("일정 불러오는중..")))
    }
}