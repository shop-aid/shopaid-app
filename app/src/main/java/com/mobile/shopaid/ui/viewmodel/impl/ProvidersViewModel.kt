package com.mobile.shopaid.ui.viewmodel.impl

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.ProvidersRepo
import com.mobile.shopaid.data.repository.impl.ProvidersRepoImpl
import com.mobile.shopaid.data.response.BanksResponseModel
import com.mobile.shopaid.ui.viewmodel.ProvidersViewModelContract
import com.mobile.shopaid.ui.viewmodel.factory.ViewModelFactory

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 19:31
 */

class ProvidersViewModel(private val providersRepo: ProvidersRepo) : ViewModel(), ProvidersViewModelContract {

    companion object {
        fun create(owner: FragmentActivity): ProvidersViewModel {
            val factory = ViewModelFactory(ProvidersViewModel(ProvidersRepoImpl()))
            return ViewModelProviders.of(owner, factory).get(ProvidersViewModel::class.java)
        }
    }

    val providersObservable by lazy {
        MediatorLiveData<ObservableResult<List<BanksResponseModel>>>()
    }

    val loadingObservable by lazy {
        MutableLiveData<Boolean>()
    }

    override fun fetchProviders() {
        loadingObservable.value = true
        fetchProvidersInternal()
    }

    private fun fetchProvidersInternal() {
        val repoData = providersRepo.fetchProviders()
        providersObservable.removeSource(repoData)
        providersObservable.addSource(repoData) {
            loadingObservable.value = false
            providersObservable.value = it
        }
    }
}