package com.mobile.shopaid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mobile.shopaid.R
import com.mobile.shopaid.extensions.animateView
import com.mobile.shopaid.extensions.getFadeInAnimator
import kotlinx.android.synthetic.main.entry_info_activity.*
import kotlinx.android.synthetic.main.entry_info_content_layout.*

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 09:41
 */

class EntryInfoActivity : BaseActivity() {

    companion object {
        private const val DELAY_ANIMATION = 1500L

        fun getStartIntent(context: Context): Intent {
            return Intent(context, EntryInfoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_info_activity)

        root.run {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }

        entryInfoNextButton.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }
}