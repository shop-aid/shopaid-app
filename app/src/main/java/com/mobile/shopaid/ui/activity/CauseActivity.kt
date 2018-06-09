package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mobile.shopaid.R
import com.mobile.shopaid.ui.fragment.CauseListFragment
import kotlinx.android.synthetic.main.cause_activity.*


class CauseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.cause_activity)

        cause_viewpager.adapter = CausePagerAdapter(supportFragmentManager)
        cause_sliding_tabs.setupWithViewPager(cause_viewpager)

        cause_next.setOnClickListener({
            startActivity(Intent(this, PartnersActivity::class.java))
        })
    }

    private inner class CausePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return CauseListFragment()
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position){
                0 -> "local"
                else -> "international"
            }
        }
    }
}
