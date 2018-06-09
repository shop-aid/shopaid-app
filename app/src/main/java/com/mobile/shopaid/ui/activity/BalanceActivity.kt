package com.mobile.shopaid.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
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

        balance_period_alltime.setOnClickListener({
            selectPeriod(0)
        })
        balance_period_year.setOnClickListener({
            selectPeriod(1)
        })
        balance_period_month.setOnClickListener({
            selectPeriod(2)
        })
        balance_period_week.setOnClickListener({
            selectPeriod(3)
        })

        selectPeriod(0)
    }

    private fun selectPeriod(selectedIndex: Int) {
        balance_period_alltime_text.setBackgroundResource(if (selectedIndex == 0) R.drawable.balance_period_background else 0)
        balance_period_year_text.setBackgroundResource(if (selectedIndex == 1) R.drawable.balance_period_background else 0)
        balance_period_month_text.setBackgroundResource(if (selectedIndex == 2) R.drawable.balance_period_background else 0)
        balance_period_week_text.setBackgroundResource(if (selectedIndex == 3) R.drawable.balance_period_background else 0)
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