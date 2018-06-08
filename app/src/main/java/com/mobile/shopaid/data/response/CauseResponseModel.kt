package com.mobile.shopaid.data.response

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 17:55
 */

data class CauseResponseModel(
        val name: String,
        val description: String,
        val local: Boolean,
        val favorite: Boolean)