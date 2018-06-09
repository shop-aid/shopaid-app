package com.mobile.shopaid.ui.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.PartnerRepo
import com.mobile.shopaid.data.repository.impl.PartnerRepoImpl
import com.mobile.shopaid.data.response.PartnerResponseModel
import com.mobile.shopaid.ui.viewmodel.factory.ViewModelFactory

class PartnerViewModel(private val partnerRepo: PartnerRepo) : ViewModel(), PartnerViewModelContract {

    companion object {
        fun create(owner: Fragment): PartnerViewModel {
            val factory = ViewModelFactory(PartnerViewModel(PartnerRepoImpl()))
            return ViewModelProviders.of(owner, factory).get(PartnerViewModel::class.java)
        }
    }

    val partnerObservable by lazy {
        MediatorLiveData<ObservableResult<List<PartnerResponseModel>>>()
    }

    val loadingObservable by lazy {
        MutableLiveData<Boolean>()
    }

    override fun fetchPartners() {
        loadingObservable.value = true
        fetchPartnersInternal()
    }

    private fun fetchPartnersInternal() {
        val repoData = partnerRepo.fetchPartners()
        partnerObservable.addSource(repoData) {
            loadingObservable.value = false
            partnerObservable.value = it
            partnerObservable.removeSource(repoData)
        }
    }
}
