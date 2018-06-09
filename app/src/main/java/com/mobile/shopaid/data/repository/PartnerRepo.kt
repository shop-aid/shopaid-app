package com.mobile.shopaid.data.repository

import android.arch.lifecycle.LiveData
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.PartnerBreakdownResponseModel
import com.mobile.shopaid.data.response.PartnerResponseModel

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:01
 */

interface PartnerRepo {

    fun fetchPartners(): LiveData<ObservableResult<List<PartnerResponseModel>>>

}