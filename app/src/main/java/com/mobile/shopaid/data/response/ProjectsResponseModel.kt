package com.mobile.shopaid.data.response

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 10:53
 */

data class ProjectsResponseModel(
        val name: String,
        val description: String,
        val local: Boolean,
        val favorite: Boolean,
        val logo: String,
        val category: String,
        val tags: List<String>)