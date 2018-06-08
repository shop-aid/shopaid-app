package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.activity_iban.*

class IbanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_iban)

        iban_next.setOnClickListener { startActivity(Intent(this, CauseActivity::class.java))}
        iban_skip.setOnClickListener { startActivity(Intent(this, CauseActivity::class.java))}
    }

}