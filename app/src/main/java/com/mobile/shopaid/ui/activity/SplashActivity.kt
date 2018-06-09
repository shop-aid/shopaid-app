package com.mobile.shopaid.ui.activity

import android.os.Bundle
import com.mobile.shopaid.R
import com.mobile.shopaid.extensions.*
import kotlinx.android.synthetic.main.splash_activity.*

class SplashActivity : BaseActivity() {

    companion object {
        private const val DELAY_ANIMATION = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        with(logoImageView) {
            animateView(getFadeInAnimator(DELAY_ANIMATION), getFadeOutAnimator(DELAY_ANIMATION)) {
                startActivity(EntryInfoActivity.getStartIntent(this@SplashActivity))
                finish()
            }
        }
    }
}
