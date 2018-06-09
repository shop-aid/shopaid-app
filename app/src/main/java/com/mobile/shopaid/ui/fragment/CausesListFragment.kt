package com.mobile.shopaid.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import com.mobile.shopaid.data.listener.FragmentItemToggleListener
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.extensions.*
import com.mobile.shopaid.ui.viewmodel.impl.CausesViewModel
import kotlinx.android.synthetic.main.causes_list_fragment.*
import kotlinx.android.synthetic.main.cause_list_row.view.*
import kotlinx.android.synthetic.main.loading_layout.*
import uk.co.chrisjenx.calligraphy.CalligraphyUtils
import kotlin.properties.Delegates

class CausesListFragment : Fragment() {

    private val causesViewModel by lazy {
        CausesViewModel.create(this)
    }

    private val causesAdapter by lazy {
        CauseAdapter()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        causesAdapter.listener = context as FragmentItemToggleListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.causes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cause_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
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

        var listener: FragmentItemToggleListener? = null
        private var expandedPosition = -1

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(cause: CauseResponseModel) {
                itemView.cause_image.loadImage(cause.poster)
                itemView.cause_name.text = cause.name
                itemView.cause_sub_text.text = cause.category
                itemView.cause_logo.loadImage(cause.logo)
                itemView.cause_item_description_container.text = cause.description
                itemView.select_item_view.setImageResource(if (cause.isActive)
                    R.drawable.icon_checkmark_selected else R.drawable.icon_checkmark_unselected)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CauseAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cause_list_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = causesList[position]
            holder.bindData(item)
            if (position == expandedPosition) {
                holder.itemView.cause_item_description_container.visibility = View.VISIBLE
                holder.itemView.cause_details_button.setImageResource(R.drawable.icon_up)
            } else {
                holder.itemView.cause_item_description_container.visibility = View.GONE
                holder.itemView.cause_details_button.setImageResource(R.drawable.icon_down)
            }
            holder.itemView.cause_details_button.setOnClickListener({
                if (expandedPosition >= 0) {
                    val prev = expandedPosition
                    notifyItemChanged(prev)
                }

                if (expandedPosition == position) {
                    expandedPosition = -1
                    notifyItemChanged(position)
                } else {
                    expandedPosition = position
                    notifyItemChanged(expandedPosition)
                }
            })
            holder.itemView.select_item_view.setOnClickListener {
                item.isActive = !item.isActive
                holder.itemView.select_item_view.setImageResource(if (item.isActive)
                    R.drawable.icon_checkmark_selected else R.drawable.icon_checkmark_unselected)
                listener?.onItemToggled(getActiveItems())
            }
            CalligraphyUtils.applyFontToTextView(holder.itemView.context,
                    holder.itemView.cause_name, "fonts/avenirnextdemibold.ttf")
        }

        override fun getItemCount() = causesList.count()

        private fun getActiveItems(): Int {
            return causesList.count { it.isActive }
        }
    }
}
