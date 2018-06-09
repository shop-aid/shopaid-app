package com.mobile.shopaid.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import com.mobile.shopaid.ui.fragment.BalanceDetailFragment
import com.mobile.shopaid.ui.fragment.BalanceOverviewFragment
import kotlinx.android.synthetic.main.balance_activity.*

class BalanceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.balance_activity)
        balance_viewpager.adapter = BalanceViewPager(supportFragmentManager)
        balannce_sliding_tabs.setupWithViewPager(balance_viewpager)
    }


    private inner class BalanceViewPager(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return if (position == 0) {
                BalanceOverviewFragment()
            } else {
                BalanceDetailFragment()
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0-> "overview"
                else-> "detail"
            }
        }
    }

}