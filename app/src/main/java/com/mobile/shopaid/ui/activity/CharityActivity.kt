package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mobile.shopaid.R
import com.mobile.shopaid.extensions.animateView
import com.mobile.shopaid.extensions.getSlideInYAnimator
import com.mobile.shopaid.extensions.waitForView
import com.mobile.shopaid.ui.fragment.CausesListFragment
import com.mobile.shopaid.ui.fragment.ProjectsListFragment
import kotlinx.android.synthetic.main.charity_activity.*

class CharityActivity : BaseActivity() {

    companion object {
        private const val DELAY_ANIMATION = 500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.charity_activity)

        cause_viewpager.adapter = CausePagerAdapter(supportFragmentManager)
        cause_sliding_tabs.setupWithViewPager(cause_viewpager)

        charityNextLayout.waitForView {
            animateView(getSlideInYAnimator(DELAY_ANIMATION))
        }

        charityNextButton.setOnClickListener({
            startActivity(Intent(this, PartnersActivity::class.java))
        })
    }

    private inner class CausePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> CausesListFragment()
                1 -> ProjectsListFragment()
                else -> {
                    CausesListFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "causes"
                else -> "projects"
            }
        }
    }
}
