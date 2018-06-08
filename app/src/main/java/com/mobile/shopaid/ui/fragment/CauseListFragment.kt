package com.mobile.shopaid.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
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
            adapter = CauseAdapter()
        }

    }

    class CauseAdapter :
            RecyclerView.Adapter<CauseAdapter.ViewHolder>() {


        class ViewHolder(val textView: ViewGroup) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): CauseAdapter.ViewHolder {

            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cause_list_row, parent, false) as ViewGroup
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.textView.text = myDataset[position]
        }

        override fun getItemCount() = 15
    }

}
