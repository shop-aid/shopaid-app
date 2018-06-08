package com.mobile.shopaid.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import com.mobile.shopaid.extensions.*
import com.mobile.shopaid.ui.entry.EntryInfoActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_ANIMATION = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        with(splashTextView) {
            animateView(getFadeInAnimator(DELAY_ANIMATION), getFadeOutAnimator(DELAY_ANIMATION)) {
                startActivity(EntryInfoActivity.getStartIntent(this@SplashActivity))
                finish()
            }
        }
    }
}
