package com.mobile.shopaid.ui.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.CausesRepo
import com.mobile.shopaid.data.repository.impl.CausesRepoImpl
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.ui.viewmodel.factory.ViewModelFactory

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:48
 */

class CausesViewModel(private val causesRepo: CausesRepo) : ViewModel(), CausesViewModelContract {

    companion object {
        fun create(owner: Fragment): CausesViewModel {
            val factory = ViewModelFactory(CausesViewModel(CausesRepoImpl()))
            return ViewModelProviders.of(owner, factory).get(CausesViewModel::class.java)
        }
    }

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
        val repoData = causesRepo.fetchCauses()
        causesObservable.addSource(repoData) {
            loadingObservable.value = false
            causesObservable.value = it
            causesObservable.removeSource(repoData)
        }
    }
}