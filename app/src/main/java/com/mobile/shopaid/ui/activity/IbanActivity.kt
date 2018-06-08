package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.iban_activity.*

class IbanActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.iban_activity)

        iban_next.setOnClickListener { startActivity(Intent(this, CauseActivity::class.java))}
        iban_skip.setOnClickListener { startActivity(Intent(this, CauseActivity::class.java))}
    }

}