package com.mobile.shopaid.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.mobile.shopaid.R
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.PartnerBreakdownResponseModel
import com.mobile.shopaid.data.response.UserResponseModel
import com.mobile.shopaid.extensions.showError
import com.mobile.shopaid.ui.viewmodel.impl.BalanceViewModel
import kotlinx.android.synthetic.main.balance_detail_fragment.*
import kotlinx.android.synthetic.main.balance_partner_breakdown_listrow.view.*
import uk.co.chrisjenx.calligraphy.CalligraphyUtils


class BalanceDetailFragment : Fragment() {

    private val balanceViewModel by lazy {
        BalanceViewModel.create(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.balance_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        balanceViewModel.userObservable.observe(this, Observer<ObservableResult<UserResponseModel>> {
            when (it) {
                is ObservableResult.Success -> initialize(it.data)
                is ObservableResult.Error -> showError(it.exception.localizedMessage)
            }
        })
    }

    private fun initialize(userResponseModel : UserResponseModel) {
        balance_detail_recyclerview.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BalanceDetailFragment.activity)
            adapter = PartnerBreakdownAdapter(userResponseModel.partner_breakdown)
        }
        val dividerItemDecoration = DividerItemDecoration(activity,
                (balance_detail_recyclerview.layoutManager as LinearLayoutManager).orientation)
        if (balance_detail_recyclerview.itemDecorationCount == 0)
            balance_detail_recyclerview.addItemDecoration(dividerItemDecoration)
    }


    class PartnerBreakdownAdapter(private val partnersBreakdownList: List<PartnerBreakdownResponseModel>) : RecyclerView.Adapter<PartnerBreakdownAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(partnerBreakdown: PartnerBreakdownResponseModel) {
                itemView.partner_breakdown_title.text = partnerBreakdown.name
                itemView.partner_breakdown_amount.text = partnerBreakdown.amount
                val params = itemView.partner_breakdown_indicator.layoutParams

                itemView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        itemView.viewTreeObserver.removeOnPreDrawListener(this)

                        params.width = (itemView.breakdown_listrow_container.width * partnerBreakdown.percentage)/100
                        itemView.partner_breakdown_indicator.layoutParams = params
                        CalligraphyUtils.applyFontToTextView(itemView.context, itemView.partner_breakdown_amount, "fonts/avenirnextdemibold.ttf")
                        return true
                    }

                })
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerBreakdownAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.balance_partner_breakdown_listrow, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(partnersBreakdownList[position])
        }

        override fun getItemCount() = partnersBreakdownList.count()
    }

}
