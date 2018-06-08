package com.mobile.shopaid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import com.mobile.shopaid.data.response.PartnerResponseModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.partner_list_row.view.*
import kotlinx.android.synthetic.main.partners_activity.*

class PartnersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.partners_activity)

        partners_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PartnersActivity)
            adapter = PartnersAdapter(mutableListOf(
                    PartnerResponseModel("test name", 0.5, "Partner name"),
                    PartnerResponseModel("test name", 0.5, "Partner name1"),
                    PartnerResponseModel("test name", 0.5, "Partner name2"),
                    PartnerResponseModel("test name", 0.5, "Partner name3")
            ))
        }

        partners_info_next.setOnClickListener({
            startActivity(Intent(this, RegistrationCompleteActivity::class.java))
        })

    }

    class PartnersAdapter(private val partnersList: List<PartnerResponseModel>) : RecyclerView.Adapter<PartnersAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(partner: PartnerResponseModel) {
                Picasso.get()
                        .load("https://www.hrwcentrach.pl/en/graph/logo-clean.png")
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
            holder.bindData(partnersList[position])
        }

        override fun getItemCount() = partnersList.count()
    }

}