package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import com.mobile.shopaid.extensions.animateView
import com.mobile.shopaid.extensions.getFadeInAnimator
import com.mobile.shopaid.extensions.waitForView
import kotlinx.android.synthetic.main.credentials_activity.*

class CredentialsActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_ANIMATION = 1500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.credentials_activity)

        root.waitForView {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }

        credentials_next.setOnClickListener({
            val intent = BalanceActivity.getStartIntent(this)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        })
    }
}