package com.mobile.shopaid.api.service

import com.mobile.shopaid.api.ApiConstants
import com.mobile.shopaid.data.response.CauseResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 17:49
 */

interface CausesService {

    @get:GET(ApiConstants.CAUSES_URL)
    val causes: Call<List<CauseResponseModel>>
}