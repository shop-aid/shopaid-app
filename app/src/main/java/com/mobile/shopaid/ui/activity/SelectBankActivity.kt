package com.mobile.shopaid.ui.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import com.mobile.shopaid.data.listener.OnListItemClickListener
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.BanksResponseModel
import com.mobile.shopaid.extensions.loadImage
import com.mobile.shopaid.extensions.showError
import com.mobile.shopaid.ui.viewmodel.impl.ProvidersViewModel
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.select_bank_activity.*
import kotlinx.android.synthetic.main.select_bank_list_row.view.*
import kotlin.properties.Delegates

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 19:19
 */

class SelectBankActivity : BaseActivity(), OnListItemClickListener<BanksResponseModel> {

    private val providersViewModel by lazy {
        ProvidersViewModel.create(this)
    }

    private val providersAdapter by lazy {
        BanksAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_bank_activity)

        banks_list_view.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = providersAdapter
        }

        initObservers()
        providersViewModel.fetchProviders()
    }

    private fun initObservers() {
        providersViewModel.providersObservable.observe(this, Observer<ObservableResult<List<BanksResponseModel>>> {
            when (it) {
                is ObservableResult.Success -> providersAdapter.bankList = it.data
                is ObservableResult.Error -> showError(it.exception.localizedMessage)
            }
        })

        providersViewModel.loadingObservable.observe(this, Observer {
            toggleLoading(it ?: false)
        })
    }

    override fun onItemClicked(position: Int, item: BanksResponseModel) {
        startActivity(Intent(this, CredentialsActivity::class.java))
    }

    private fun toggleLoading(isLoading: Boolean) {
        loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    class BanksAdapter(val listener: OnListItemClickListener<BanksResponseModel>) : RecyclerView.Adapter<BanksAdapter.ViewHolder>() {

        var bankList: List<BanksResponseModel> by Delegates.observable(ArrayList()) { _, _, _ ->
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.select_bank_list_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(bankList[position])
        }

        override fun getItemCount() = bankList.count()

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                listener.onItemClicked(adapterPosition, bankList[adapterPosition])
            }

            fun bindData(bank: BanksResponseModel) {
                itemView.bank_name.text = bank.name
                itemView.bank_icon.loadImage(bank.logo)
            }
        }
    }
}