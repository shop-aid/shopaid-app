package com.mobile.shopaid.api.service

import com.mobile.shopaid.api.ApiConstants
import com.mobile.shopaid.data.response.PartnerBreakdownResponseModel
import com.mobile.shopaid.data.response.PartnerResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 17:49
 */

interface PartnerService {

    @get:GET(ApiConstants.PARTNERS_URL)
    val partners: Call<List<PartnerResponseModel>>
}