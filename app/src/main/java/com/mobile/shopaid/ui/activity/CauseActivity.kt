package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.cause_activity.*
import android.support.v4.app.FragmentStatePagerAdapter
import com.mobile.shopaid.ui.fragment.CauseListFragment


class CauseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.cause_activity)

        cause_viewpager.adapter = CausePagerAdapter(supportFragmentManager)

        cause_next.setOnClickListener({
            startActivity(Intent(this, PartnersActivity::class.java))
        })
    }

    private inner class CausePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return CauseListFragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
