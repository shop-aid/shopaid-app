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
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.extensions.showError
import com.mobile.shopaid.ui.viewmodel.CausesViewModel
import kotlinx.android.synthetic.main.causes_list_fragment.*
import kotlinx.android.synthetic.main.cause_list_row.view.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlin.properties.Delegates



class CausesListFragment : Fragment() {

    private val causesViewModel by lazy {
        CausesViewModel.create(this)
    }

    private val causesAdapter by lazy {
        CauseAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.causes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cause_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CausesListFragment.activity)
            adapter = causesAdapter
        }

        initObservers()
        causesViewModel.fetchCauses()
    }

    private fun initObservers() {
        causesViewModel.causesObservable.observe(this, Observer<ObservableResult<List<CauseResponseModel>>> {
            when (it) {
                is ObservableResult.Success -> causesAdapter.causesList = it.data
                is ObservableResult.Error -> showError(it.exception.localizedMessage)
            }
        })

        causesViewModel.loadingObservable.observe(this, Observer {
            toggleLoading(it ?: false)
        })
    }

    private fun toggleLoading(isLoading: Boolean) {
        loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    class CauseAdapter : RecyclerView.Adapter<CauseAdapter.ViewHolder>() {

        var causesList: List<CauseResponseModel> by Delegates.observable(ArrayList()) { _, _, _ ->
            notifyDataSetChanged()
        }

        private var expandedPosition = -1

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(cause: CauseResponseModel) {
                itemView.cause_item_name.text = cause.name
                itemView.causeTextView.text = cause.description
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CauseAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cause_list_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(causesList[position])
            if (position == expandedPosition) {
                holder.itemView.cause_item_description_container.visibility = View.VISIBLE
            } else {
                holder.itemView.cause_item_description_container.visibility = View.GONE
            }
            holder.itemView.cause_item_name.setOnClickListener({
                if (expandedPosition >= 0) {
                    val prev = expandedPosition
                    notifyItemChanged(prev)
                }

                expandedPosition = position
                notifyItemChanged(expandedPosition)
            })
        }

        override fun getItemCount() = causesList.count()
    }
}