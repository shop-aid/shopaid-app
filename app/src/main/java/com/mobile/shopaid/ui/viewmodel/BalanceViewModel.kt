package com.mobile.shopaid.ui.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.SupportActivity
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.UserRepo
import com.mobile.shopaid.data.repository.impl.UserRepoImpl
import com.mobile.shopaid.data.response.UserResponseModel
import com.mobile.shopaid.ui.viewmodel.factory.ViewModelFactory

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:48
 */

class BalanceViewModel(private val userRepo: UserRepo) : ViewModel(), BalanceViewModelContract {

    companion object {
        fun create(owner: FragmentActivity): BalanceViewModel {
            val factory = ViewModelFactory(BalanceViewModel(UserRepoImpl()))
            return ViewModelProviders.of(owner, factory).get(BalanceViewModel::class.java)
        }
    }

    val userbservable by lazy {
        MediatorLiveData<ObservableResult<UserResponseModel>>()
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