package com.mobile.shopaid.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.credentials_activity.*

class CredentialsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.credentials_activity)

        credentials_next.setOnClickListener({
            // TODO
        })
    }

}