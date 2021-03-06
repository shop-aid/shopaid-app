package com.mobile.shopaid

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.mobile.shopaid.ui.activity.PartnersActivity
import com.mobile.shopaid.ui.activity.SplashActivity
import com.mobile.shopaid.ui.fragment.CausesListFragment
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.balance_activity.*
import kotlinx.android.synthetic.main.registration_complete_activity.*
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
        Thread.sleep(1000)
        onView(allOf<View>(withId(R.id.entryInfoNextButton))).perform(click())
        onView(withId(R.id.login_email)).perform(typeText("petarboy69@icemobile.com"))
        onView(allOf<View>(withId(R.id.login_sign_in_button))).perform(click())
//        Thread.sleep(10000)
//        onView(withId(R.id.iban_iban_edittext)).perform(typeText("NL11INGB0008249866"))
//        onView(allOf<View>(withId(R.id.iban_next))).perform(click())

        Thread.sleep(1000)
//        onView(allOf(withText("projects"))).perform(click())
//        Thread.sleep(1000)
//        onView(allOf(withText("causes"))).perform(click())
        onView(allOf(ViewMatchers.withId(R.id.cause_recyclerview), isCompletelyDisplayed()))
                .perform(
                        RecyclerViewActions.scrollToPosition<CausesListFragment.CauseAdapter.ViewHolder>(5),
                        RecyclerViewActions.actionOnItemAtPosition<CausesListFragment.CauseAdapter.ViewHolder>(5, click()))

        Thread.sleep(1000)
        onView(allOf<View>(withId(R.id.charityNextButton))).perform(click())
        Thread.sleep(1000)
        onView(allOf(ViewMatchers.withId(R.id.partners_recyclerview), isCompletelyDisplayed()))
                .perform(
                        RecyclerViewActions.scrollToPosition<PartnersActivity.PartnersAdapter.ViewHolder>(5),
                        RecyclerViewActions.actionOnItemAtPosition<PartnersActivity.PartnersAdapter.ViewHolder>(5, click()))
        onView(allOf<View>(withId(R.id.partners_info_next))).perform(click())
        onView(allOf<View>(withId(R.id.registrationCompleteTextView))).perform(click())

        Thread.sleep(10000)
        onView(allOf<View>(withText("Transactions"))).perform(click())
        Thread.sleep(1000)
        onView(allOf<View>(withText("Overview"))).perform(click())
        Thread.sleep(100000)
        onView(allOf<View>(withId(R.id.balance_period_year))).perform(click())
        Thread.sleep(1000)
        onView(allOf<View>(withId(R.id.balance_period_month))).perform(click())
        Thread.sleep(1000)
        onView(allOf<View>(withId(R.id.balance_period_week))).perform(click())
        Thread.sleep(1000)
        onView(allOf<View>(withId(R.id.balance_period_alltime))).perform(click())

        Thread.sleep(1000000)
        assertTrue(true)
    }
}
