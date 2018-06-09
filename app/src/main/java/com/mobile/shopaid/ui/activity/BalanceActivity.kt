package com.mobile.shopaid.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import com.mobile.shopaid.R
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.UserResponseModel
import com.mobile.shopaid.extensions.showError
import com.mobile.shopaid.ui.fragment.BalanceDetailFragment
import com.mobile.shopaid.ui.fragment.BalanceOverviewFragment
import com.mobile.shopaid.ui.viewmodel.BalanceViewModel
import kotlinx.android.synthetic.main.balance_activity.*
import kotlinx.android.synthetic.main.loading_layout.*
import uk.co.chrisjenx.calligraphy.CalligraphyUtils

class BalanceActivity : BaseActivity() {

    private val balanceViewModel by lazy {
        BalanceViewModel.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.balance_activity)

        initObservers()
        balanceViewModel.fetchUser()
    }

    private fun initObservers() {
        balanceViewModel.userbservable.observe(this, Observer<ObservableResult<UserResponseModel>> {
            when (it) {
                is ObservableResult.Success -> init(it.data)
                is ObservableResult.Error -> showError(it.exception.localizedMessage)
            }
        })

        balanceViewModel.loadingObservable.observe(this, Observer {
            toggleLoading(it ?: false)
        })
    }

    private fun toggleLoading(isLoading: Boolean) {
        loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun init(userModel : UserResponseModel) {
        CalligraphyUtils.applyFontToTextView(this, balance_amount, "fonts/avenirnextdemibold.ttf")
        balance_viewpager.adapter = BalanceViewPager(supportFragmentManager)
        balance_amount.text = userModel.charity_balance
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
                0-> "Overview"
                else-> "Transactions"
            }
        }
    }

}