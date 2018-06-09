package com.mobile.shopaid.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.CauseBreakdownReponseModel
import com.mobile.shopaid.data.response.UserResponseModel
import com.mobile.shopaid.extensions.showError
import com.mobile.shopaid.ui.viewmodel.BalanceViewModel
import kotlinx.android.synthetic.main.balance_cause_breakdown_listrow.view.*
import kotlinx.android.synthetic.main.balance_overview_fragment.*
import android.support.v7.widget.DividerItemDecoration
import android.widget.TextView


class BalanceOverviewFragment : Fragment() {

    private val balanceViewModel by lazy {
        BalanceViewModel.create(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.balance_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        balanceViewModel.userbservable.observe(this, Observer<ObservableResult<UserResponseModel>> {
            when (it) {
                is ObservableResult.Success -> init(it.data)
                is ObservableResult.Error -> showError(it.exception.localizedMessage)
            }
        })
    }

    private fun init(userResponseModel : UserResponseModel) {
        (percentage_row1_container.findViewById<TextView>(R.id.balance_percentage_row_percentage)).text = userResponseModel.cause_breakdown[0].percentage.toString() + "%"
        (percentage_row2_container.findViewById<TextView>(R.id.balance_percentage_row_percentage)).text = userResponseModel.cause_breakdown[1].percentage.toString() + "%"
        (percentage_row3_container.findViewById<TextView>(R.id.balance_percentage_row_percentage)).text = userResponseModel.cause_breakdown[2].percentage.toString() + "%"
        (percentage_row1_container.findViewById<TextView>(R.id.balance_percentage_row_cause)).text = userResponseModel.cause_breakdown[0].name
        (percentage_row2_container.findViewById<TextView>(R.id.balance_percentage_row_cause)).text = userResponseModel.cause_breakdown[1].name
        (percentage_row3_container.findViewById<TextView>(R.id.balance_percentage_row_cause)).text = userResponseModel.cause_breakdown[2].name


        balance_overview_recyclerview.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BalanceOverviewFragment.activity)
            adapter = CauseBreakdownAdapter(userResponseModel.cause_breakdown)
        }
        val dividerItemDecoration = DividerItemDecoration(activity,
                (balance_overview_recyclerview.layoutManager as LinearLayoutManager).orientation)
        balance_overview_recyclerview.addItemDecoration(dividerItemDecoration)
    }

    class CauseBreakdownAdapter(private val causeBreakdownList: List<CauseBreakdownReponseModel>) : RecyclerView.Adapter<CauseBreakdownAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(causeBreakdown: CauseBreakdownReponseModel) {
                itemView.cause_breakdown_title.text = causeBreakdown.name
                itemView.cause_breakdown_amount.text = causeBreakdown.amount
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CauseBreakdownAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.balance_cause_breakdown_listrow, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(causeBreakdownList[position])
        }

        override fun getItemCount() = causeBreakdownList.count()
    }


}
