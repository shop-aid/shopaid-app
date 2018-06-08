package com.mobile.shopaid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stores_info.*

class StoresInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_stores_info)

        stores_info_next.setOnClickListener({
            startActivity(Intent(this, BalanceActivity::class.java))
        })
    }
}