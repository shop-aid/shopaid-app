package com.mobile.shopaid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.shopaid.R
import kotlinx.android.synthetic.main.partners_activity.*

class PartnersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.partners_activity)

        partners_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PartnersActivity)
            adapter = PartnersAdapter(ArrayList(), this@PartnersActivity)
        }

        partners_info_next.setOnClickListener({
            startActivity(Intent(this, RegistrationCompleteActivity::class.java))
        })

    }

    class PartnersAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


        // Gets the number of animals in the list
        override fun getItemCount(): Int {
            return 15
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.partner_list_row, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder?.tvAnimalType?.text = items.get(position)
        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
//        val tvAnimalType = view.tv_animal_type
    }

}