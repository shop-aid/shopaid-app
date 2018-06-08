package com.mobile.shopaid.data.repository

import android.arch.lifecycle.LiveData
import com.mobile.shopaid.data.CauseResponseModel
import com.mobile.shopaid.data.observable.ObservableResult

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:01
 */

interface CausesRepo {

    fun fetchCauses()

    fun getCausesObservable(): LiveData<ObservableResult<List<CauseResponseModel>>>
}