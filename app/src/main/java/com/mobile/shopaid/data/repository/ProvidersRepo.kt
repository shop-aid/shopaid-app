package com.mobile.shopaid.data.repository

import android.arch.lifecycle.LiveData
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.BanksResponseModel

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 19:26
 */

interface ProvidersRepo {

    fun fetchProviders(): LiveData<ObservableResult<List<BanksResponseModel>>>
}