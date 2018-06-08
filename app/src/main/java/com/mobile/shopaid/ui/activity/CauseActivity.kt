package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.activity_cause.*

class CauseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cause)
        partners_next.setOnClickListener({
            startActivity(Intent(this, PartnersActivity::class.java))
        })
    }
}
