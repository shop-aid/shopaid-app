package com.mobile.shopaid.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.BanksResponseModel
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.extensions.loadImage
import com.mobile.shopaid.extensions.showError
import com.mobile.shopaid.ui.viewmodel.ProvidersViewModel
import kotlinx.android.synthetic.main.causes_list_fragment.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.select_bank_activity.*
import kotlinx.android.synthetic.main.select_bank_list_row.view.*
import kotlin.properties.Delegates

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 19:19
 */

class SelectBankActivity : BaseActivity() {

    private val providersViewModel by lazy {
        ProvidersViewModel.create(this)
    }

    private val providersAdapter by lazy {
        BanksAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_bank_activity)

        banks_list_view.apply {
            layoutManager = LinearLayoutManager(context)
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

    private fun toggleLoading(isLoading: Boolean) {
        loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    class BanksAdapter : RecyclerView.Adapter<BanksAdapter.ViewHolder>() {

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

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(bank: BanksResponseModel) {
                itemView.bank_name.text = bank.name
                itemView.bank_icon.loadImage(bank.logo)
            }
        }
    }
}