package com.mobile.shopaid.ui.activity

import android.os.Bundle
import android.os.Handler
import com.mobile.shopaid.R
import com.mobile.shopaid.extensions.animateView
import com.mobile.shopaid.extensions.getFadeInAnimator
import com.mobile.shopaid.extensions.waitForView
import kotlinx.android.synthetic.main.charity_activity.*

class RegistrationCompleteActivity : BaseActivity() {

    companion object {
        private const val DELAY_ANIMATION = 1500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.registration_complete_activity)

        root.waitForView {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }

        Handler().postDelayed({
            startActivity(BalanceActivity.getStartIntent(this))
        }, 3000)

    }

}