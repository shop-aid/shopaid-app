package com.mobile.shopaid.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import com.mobile.shopaid.data.response.PartnerBreakdownResponseModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.balance_detail_fragment.*
import kotlinx.android.synthetic.main.partner_list_row.view.*


class BalanceDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.balance_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        balance_detail_recyclerview.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BalanceDetailFragment.activity)
            adapter = PartnerBreakdownAdapter(emptyList())
        }
    }

    class PartnerBreakdownAdapter(private val partnersBreakdownList: List<PartnerBreakdownResponseModel>) : RecyclerView.Adapter<PartnerBreakdownAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(partnerBreakdown: PartnerBreakdownResponseModel) {
                itemView.partner_name.text = partnerBreakdown.name
                itemView.partner_percentage.text = partnerBreakdown.amount
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerBreakdownAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.partner_list_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(partnersBreakdownList[position])
        }

        override fun getItemCount() = partnersBreakdownList.count()
    }

}
