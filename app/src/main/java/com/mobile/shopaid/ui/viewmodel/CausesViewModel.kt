package com.mobile.shopaid.ui.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.CausesRepo
import com.mobile.shopaid.data.response.CauseResponseModel

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:48
 */

class CausesViewModel(private val causesRepo: CausesRepo) : CausesViewModelContract {

    val causesObservable by lazy {
        MediatorLiveData<ObservableResult<List<CauseResponseModel>>>()
    }
    val loadingObservable by lazy {
        MutableLiveData<Boolean>()
    }

    override fun fetchCauses() {
        loadingObservable.value = true
        fetchCausesInternal()
    }

    private fun fetchCausesInternal() {
        causesObservable.addSource(causesRepo.fetchCauses()) {
            loadingObservable.value = false
            causesObservable.value = it
        }
    }
}