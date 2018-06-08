package com.mobile.shopaid.data.repository

import android.arch.lifecycle.LiveData
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.CauseResponseModel

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:01
 */

interface CausesRepo {

    fun fetchCauses(): LiveData<ObservableResult<List<CauseResponseModel>>>
}