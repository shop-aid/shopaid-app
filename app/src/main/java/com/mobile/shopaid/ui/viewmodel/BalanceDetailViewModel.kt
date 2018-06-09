package com.mobile.shopaid.ui.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.CausesRepo
import com.mobile.shopaid.data.repository.UserRepo
import com.mobile.shopaid.data.repository.impl.CausesRepoImpl
import com.mobile.shopaid.data.repository.impl.UserRepoImpl
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.data.response.UserResponseModel
import com.mobile.shopaid.ui.viewmodel.factory.ViewModelFactory

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:48
 */

class BalanceDetailViewModel(private val userRepo: UserRepo) : ViewModel(), BalanceDetailViewModelContract {

    companion object {
        fun create(owner: Fragment): BalanceDetailViewModel {
            val factory = ViewModelFactory(BalanceDetailViewModel(UserRepoImpl()))
            return ViewModelProviders.of(owner, factory).get(BalanceDetailViewModel::class.java)
        }
    }

    val userbservable by lazy {
        MediatorLiveData<ObservableResult<List<UserResponseModel>>>()
    }

    val loadingObservable by lazy {
        MutableLiveData<Boolean>()
    }

    override fun fetchUser() {
        loadingObservable.value = true
        fetchUsersInternal()
    }

    private fun fetchUsersInternal() {
        val repoData = userRepo.fetchUser()
        userbservable.addSource(repoData) {
            loadingObservable.value = false
            userbservable.value = it
            userbservable.removeSource(repoData)
        }
    }
}