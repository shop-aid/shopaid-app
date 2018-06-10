package com.mobile.shopaid.ui.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import com.mobile.shopaid.R
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.UserResponseModel
import com.mobile.shopaid.extensions.animateView
import com.mobile.shopaid.extensions.getFadeInAnimator
import com.mobile.shopaid.extensions.showError
import com.mobile.shopaid.extensions.waitForView
import com.mobile.shopaid.ui.fragment.BalanceDetailFragment
import com.mobile.shopaid.ui.fragment.BalanceOverviewFragment
import com.mobile.shopaid.ui.viewmodel.impl.BalanceViewModel
import kotlinx.android.synthetic.main.balance_activity.*
import kotlinx.android.synthetic.main.balance_empty_state.*
import kotlinx.android.synthetic.main.loading_layout.*
import uk.co.chrisjenx.calligraphy.CalligraphyUtils

class BalanceActivity : BaseActivity() {

    companion object {

        const val EXTRA_SHOW_BALANCE = "extra_show_balance"
        private const val DELAY_ANIMATION = 1500L

        fun getStartIntent(context: Context): Intent {
            return Intent(context, BalanceActivity::class.java)
        }
    }

    private val balanceViewModel by lazy {
        BalanceViewModel.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.balance_activity)
        balance_header.setOnClickListener({ startActivity(Intent(this, SelectBankActivity::class.java)) })
        initObservers()
        balanceViewModel.fetchUser()
    }

    private fun initObservers() {
        balanceViewModel.userObservable.observe(this, Observer<ObservableResult<UserResponseModel>> {
            when (it) {
                is ObservableResult.Success -> populateUI(it.data)
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

    private fun populateUI(userModel: UserResponseModel) {
        root.waitForView {
            animateView(getFadeInAnimator(DELAY_ANIMATION))
        }

        CalligraphyUtils.applyFontToTextView(this, balance_amount, "fonts/avenirnextdemibold.ttf")
        balance_viewpager.adapter = BalanceViewPager(supportFragmentManager)
        balance_viewpager.adapter?.notifyDataSetChanged()
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

        swipe.isRefreshing = false
        swipe.setOnRefreshListener {
            balanceViewModel.fetchUser()
        }
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
                0 -> "Overview"
                else -> "Transactions"
            }
        }
    }

}