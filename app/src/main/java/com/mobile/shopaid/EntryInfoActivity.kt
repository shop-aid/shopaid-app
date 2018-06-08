package com.mobile.shopaid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.extensions.animateView
import com.mobile.shopaid.extensions.getFadeInAnimator
import kotlinx.android.synthetic.main.activity_entry_info.*
import kotlinx.android.synthetic.main.layout_entry_info_content.*

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 09:41
 */

class EntryInfoActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_ANIMATION = 2000L

        fun getStartIntent(context: Context): Intent {
            return Intent(context, EntryInfoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_info)

        with(entryInfoHeaderView) {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }

        with(entryInfoView) {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }

        with(entryInfoNextButton) {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }
    }
}