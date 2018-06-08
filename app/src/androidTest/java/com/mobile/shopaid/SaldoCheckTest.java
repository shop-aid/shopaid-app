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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SaldoCheckTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void saldoCheckTest() {
        onView(allOf(withId(R.id.entryInfoNextButton))).perform(click());
        onView(allOf(withId(R.id.login_sign_in_button))).perform(click());
        onView(allOf(withId(R.id.partners_next))).perform(click());
        onView(allOf(withId(R.id.stores_info_next))).perform(click());
    }
}
