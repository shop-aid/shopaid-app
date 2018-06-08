package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.registration_complete_activity.*

class RegistrationCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.registration_complete_activity)

        registration_complete_next.setOnClickListener({
            startActivity(Intent(this, BalanceActivity::class.java))
        })
    }

}