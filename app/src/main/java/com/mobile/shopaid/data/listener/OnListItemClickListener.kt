package com.mobile.shopaid.data.listener

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 22:31
 */

interface OnListItemClickListener<in T> {

    fun onItemClicked(position: Int, item: T)
}