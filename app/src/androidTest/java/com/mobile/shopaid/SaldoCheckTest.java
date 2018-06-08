package com.mobile.shopaid;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.mobile.shopaid.ui.activity.splash.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SaldoCheckTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void saldoCheckTest() throws InterruptedException {
        onView(allOf(withId(R.id.entryInfoNextButton))).perform(click());
        onView(withId(R.id.login_email)).perform(typeText("petarboy69@icemobile.com"));
        onView(allOf(withId(R.id.login_sign_in_button))).perform(click());
        onView(withId(R.id.iban_iban_edittext)).perform(typeText("NL11INGB0008249866"));
        onView(allOf(withId(R.id.iban_next))).perform(click());
        onView(allOf(withId(R.id.cause_local))).perform(click());
        Thread.sleep(10000);
        onView(allOf(withId(R.id.cause_international))).perform(click());
        onView(allOf(withId(R.id.cause_local))).perform(click());
        onView(allOf(withId(R.id.cause_next))).perform(click());
        onView(allOf(withId(R.id.stores_info_next))).perform(click());
        onView(allOf(withId(R.id.registration_complete_next))).perform(click());
    }
}
