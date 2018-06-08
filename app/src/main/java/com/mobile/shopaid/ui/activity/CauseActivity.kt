package com.mobile.shopaid.ui.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.cause_activity.*
import android.support.v4.app.FragmentStatePagerAdapter
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.ui.fragment.CauseListFragment
import com.mobile.shopaid.ui.viewmodel.CausesViewModel


class CauseActivity : AppCompatActivity() {

    private val causesViewModel by lazy {
        CausesViewModel.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.cause_activity)

        cause_viewpager.adapter = CausePagerAdapter(supportFragmentManager)

        cause_next.setOnClickListener({
            startActivity(Intent(this, PartnersActivity::class.java))
        })

        initObservers()

        causesViewModel.fetchCauses()
    }

    private inner class CausePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return CauseListFragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }

    private fun initObservers() {
        causesViewModel.causesObservable.observe(this, Observer<ObservableResult<List<CauseResponseModel>>> {
            when (it) {
                is ObservableResult.Success -> println("Size ${it.data.count()}")
                is ObservableResult.Error -> println("Error ${it.exception.localizedMessage}")
            }
        })

        causesViewModel.loadingObservable.observe(this, Observer {
            when (it) {
                true -> println("Start loading...")
                false -> println("Stop loading...")
            }
        })
    }

}
