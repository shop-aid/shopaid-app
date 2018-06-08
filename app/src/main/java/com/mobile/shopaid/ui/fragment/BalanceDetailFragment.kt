package com.mobile.shopaid.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.balance_detail_fragment.*


class BalanceDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.balance_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        balance_detail_recyclerview.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BalanceDetailFragment.activity)
            adapter = BalanceDetailAdapter()
        }
    }

    class BalanceDetailAdapter :
            RecyclerView.Adapter<BalanceDetailAdapter.ViewHolder>() {

        // Each data item is just a string in this case that is shown in a TextView.
        class ViewHolder(val textView: ViewGroup) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): BalanceDetailAdapter.ViewHolder {

            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.balance_detail_list_row, parent, false) as ViewGroup

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.textView.text = myDataset[position]
        }
        override fun getItemCount() = 15
    }

}
