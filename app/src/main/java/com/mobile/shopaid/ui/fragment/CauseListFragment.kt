package com.mobile.shopaid.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.mobile.shopaid.R
import com.mobile.shopaid.data.response.CauseResponseModel
import kotlinx.android.synthetic.main.cause_list_fragment.*

class CauseListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cause_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cause_recyclerview.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CauseListFragment.activity)
            adapter = CauseAdapter(mutableListOf(
                    CauseResponseModel("Test name", "Test description", true, true, "logo url1", "category1", emptyList()),
                    CauseResponseModel("Test name222", "Test description222", true, true, "logo url2", "category2", emptyList())
            ))
        }

    }

    class CauseAdapter(val causes : List<CauseResponseModel> ) :
            RecyclerView.Adapter<CauseAdapter.ViewHolder>() {

        class ViewHolder(root: ViewGroup) : RecyclerView.ViewHolder(root) {
            var name = root.findViewById(R.id.cause_item_name) as TextView
            var description = root.findViewById(R.id.cause_item_description) as TextView
            var checkbox = root.findViewById(R.id.cause_item_checkbox) as CheckBox
            var category = root.findViewById(R.id.cause_item_category) as TextView
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): CauseAdapter.ViewHolder {

            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cause_list_row, parent, false) as ViewGroup

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            causes.get(position)
            holder.name.text = causes.get(position).name
            holder.description.text = causes.get(position).description
            holder.category.text = causes.get(position).description

        }

        override fun getItemCount() = causes.size
    }

}
