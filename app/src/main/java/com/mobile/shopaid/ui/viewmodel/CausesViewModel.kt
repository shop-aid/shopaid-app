package com.mobile.shopaid.ui.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.CharityRepo
import com.mobile.shopaid.data.repository.impl.CharityRepoImpl
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.ui.viewmodel.factory.ViewModelFactory

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:48
 */

class CausesViewModel(private val charityRepo: CharityRepo) : ViewModel(), CausesViewModelContract {

    companion object {
        fun create(owner: Fragment): CausesViewModel {
            val factory = ViewModelFactory(CausesViewModel(CharityRepoImpl()))
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
        val repoData = charityRepo.fetchCauses()
        causesObservable.removeSource(repoData)
        causesObservable.addSource(repoData) {
            loadingObservable.value = false
            causesObservable.value = it
        }
    }
}