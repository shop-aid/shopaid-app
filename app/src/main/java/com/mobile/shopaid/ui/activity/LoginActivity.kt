package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity)
        login_sign_in_button.setOnClickListener({
            startActivity(Intent(this, IbanActivity::class.java))
        })
    }
}