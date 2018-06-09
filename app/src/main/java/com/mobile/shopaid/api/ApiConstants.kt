package com.mobile.shopaid.api

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 17:44
 */

object ApiConstants {

    const val URL_SCHEME = "https"
    const val BASE_URL = "abn-shopaid.herokuapp.com"
    private const val API_V1_URL = "/api/v1/"

    const val CAUSES_URL = "${API_V1_URL}causes.json"
    const val PARTNERS_URL = "${API_V1_URL}partners.json"
    const val USERS_URL = "${API_V1_URL}users.json"
    const val PROJECTS_URL = "${API_V1_URL}projects.json"
}