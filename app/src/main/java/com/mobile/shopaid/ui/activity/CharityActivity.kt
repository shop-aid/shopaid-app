package com.mobile.shopaid.ui.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mobile.shopaid.R
import com.mobile.shopaid.data.listener.FragmentItemToggleListener
import com.mobile.shopaid.extensions.*
import com.mobile.shopaid.ui.fragment.CausesListFragment
import com.mobile.shopaid.ui.fragment.ProjectsListFragment
import kotlinx.android.synthetic.main.charity_activity.*

class CharityActivity : BaseActivity(), FragmentItemToggleListener {

    companion object {
        private const val DELAY_ANIMATION = 100L
        private const val DELAY_ROOT_ANIMATION = 1000L
    }

    private var isCounterShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.charity_activity)

        root.waitForView {
            animateView(getFadeInAnimator(DELAY_ROOT_ANIMATION))
        }

        cause_viewpager.adapter = CausePagerAdapter(supportFragmentManager)
        cause_sliding_tabs.setupWithViewPager(cause_viewpager)

        charityNextButton.setOnClickListener({
            startActivity(Intent(this, PartnersActivity::class.java))
        })
    }

    override fun onItemToggled(activeItemsCount: Int) {
        charitySelectedView.text = getString(R.string.text_selected, activeItemsCount)
        val animator: ObjectAnimator = if (isCounterShown && activeItemsCount == 0) {
            isCounterShown = false
            charityNextLayout.getSlideOutYAnimator(DELAY_ANIMATION)
        } else if (!isCounterShown && activeItemsCount > 0) {
            isCounterShown = true
            charityNextLayout.getSlideInYAnimator(DELAY_ANIMATION)
        } else return

        charityNextLayout.run {
            animateView(animator)
        }
    }

    private inner class CausePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ProjectsListFragment()
                1 -> CausesListFragment()
                else -> {
                    ProjectsListFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Projects"
                else -> "Organizations"
            }
        }
    }
}
