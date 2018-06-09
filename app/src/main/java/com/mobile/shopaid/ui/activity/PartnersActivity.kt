package com.mobile.shopaid.ui.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.PartnerResponseModel
import com.mobile.shopaid.ui.viewmodel.PartnerViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.partner_list_row.view.*
import kotlinx.android.synthetic.main.partners_activity.*
import kotlin.properties.Delegates

class PartnersActivity : BaseActivity() {

    private val partnerViewModel by lazy {
        PartnerViewModel.create(this)
    }

    private val partnerAdapter by lazy {
        PartnersActivity.PartnersAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.partners_activity)

        partners_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PartnersActivity)
            adapter = partnerAdapter
        }

        initObservers()
        partnerViewModel.fetchPartners()

        partners_info_next.setOnClickListener({
            startActivity(Intent(this, RegistrationCompleteActivity::class.java))
        })
    }

    private fun initObservers() {
        partnerViewModel.partnerObservable.observe(this, Observer<ObservableResult<List<PartnerResponseModel>>> {
            when (it) {
                is ObservableResult.Success -> partnerAdapter.partnerList = it.data
//                is ObservableResult.Error -> showError(it.exception.localizedMessage)
            }
        })

        partnerViewModel.loadingObservable.observe(this, Observer {
            toggleLoading(it ?: false)
        })
    }

    private fun toggleLoading(isLoading: Boolean) {
        loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    class PartnersAdapter : RecyclerView.Adapter<PartnersAdapter.ViewHolder>() {

        var partnerList: List<PartnerResponseModel> by Delegates.observable(ArrayList()) { _, _, _ ->
            notifyDataSetChanged()
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(partner: PartnerResponseModel) {
                Picasso.get()
                        .load(partner.logo)
                        .into(itemView.partner_logo)


                itemView.partner_name.text = partner.name
                itemView.partner_percentage.text = partner.percentage.toString()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnersAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.partner_list_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(partnerList[position])
        }

        override fun getItemCount() = partnerList.count()
    }
}