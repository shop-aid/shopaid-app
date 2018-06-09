package com.mobile.shopaid.api.service

import com.mobile.shopaid.api.ApiConstants
import com.mobile.shopaid.data.response.BanksResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 19:23
 */

interface ProvidersService {

    @get:GET(ApiConstants.PROVIDERS_URL)
    val providers: Call<List<BanksResponseModel>>
}