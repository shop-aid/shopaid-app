package com.mobile.shopaid.ui.activity

import android.os.Bundle
import android.os.Handler
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.registration_complete_activity.*

class RegistrationCompleteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.registration_complete_activity)

        Handler().postDelayed(Runnable() {
            startActivity(BalanceActivity.getStartIntent(this, false))
        }, 3000)

    }

}