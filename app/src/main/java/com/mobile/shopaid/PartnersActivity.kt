package com.mobile.shopaid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_partners.*

class PartnersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_partners)
        partners_next.setOnClickListener({
            startActivity(Intent(this, StoresInfoActivity::class.java))
        })
    }
}
