package com.mobile.shopaid

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.mobile.shopaid.ui.activity.SplashActivity
import com.mobile.shopaid.ui.fragment.CauseListFragment
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class BalanceCheckTestKotlinForPetar {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    @Throws(InterruptedException::class)
    fun saldoCheckTest() {
        IdlingPolicies.setMasterPolicyTimeout(15, TimeUnit.SECONDS)
        Thread.sleep(2000)
        onView(allOf<View>(withId(R.id.entryInfoNextButton))).perform(click())
        onView(withId(R.id.login_email)).perform(typeText("petarboy69@icemobile.com"))
        onView(allOf<View>(withId(R.id.login_sign_in_button))).perform(click())
        onView(withId(R.id.iban_iban_edittext)).perform(typeText("NL11INGB0008249866"))
        onView(allOf<View>(withId(R.id.iban_next))).perform(click())
        onView(allOf<View>(withId(R.id.cause_local))).perform(click())
        onView(allOf(ViewMatchers.withId(R.id.cause_recyclerview), isCompletelyDisplayed()))
                .perform(
                        RecyclerViewActions.scrollToPosition<CauseListFragment.CauseAdapter.ViewHolder>(5),
                        RecyclerViewActions.actionOnItemAtPosition<CauseListFragment.CauseAdapter.ViewHolder>(5, click()))

        Thread.sleep(2000)
//        onView(allOf<View>(withId(R.id.cause_international))).perform(click())
//        onView(allOf<View>(withId(R.id.cause_local))).perform(click())
        onView(allOf<View>(withId(R.id.cause_next))).perform(click())
        Thread.sleep(5000)
        onView(allOf<View>(withId(R.id.partners_info_next))).perform(click())
        onView(allOf<View>(withId(R.id.registration_complete_next))).perform(click())

        // switch between tabs on balance page
        Thread.sleep(2000)
        onView(allOf<View>(withId(R.id.balance_detail_tab))).perform(click())
        Thread.sleep(2000)
        onView(allOf<View>(withId(R.id.balance_overview_tab))).perform(click())
        assertTrue(true)
    }
}
