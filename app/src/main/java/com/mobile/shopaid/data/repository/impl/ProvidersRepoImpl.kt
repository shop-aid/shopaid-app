package com.mobile.shopaid.data.repository.impl

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mobile.shopaid.api.client.RetrofitClient
import com.mobile.shopaid.api.service.PartnerService
import com.mobile.shopaid.api.service.ProvidersService
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.ProvidersRepo
import com.mobile.shopaid.data.response.BanksResponseModel
import com.mobile.shopaid.data.response.PartnerResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 19:27
 */

class ProvidersRepoImpl : ProvidersRepo {

    private val providersService = RetrofitClient.retrofit.create(ProvidersService::class.java)
    private val providersObservable by lazy {
        MutableLiveData<ObservableResult<List<BanksResponseModel>>>()
    }

    override fun fetchProviders(): LiveData<ObservableResult<List<BanksResponseModel>>> {
        providersService.providers.enqueue(object : Callback<List<BanksResponseModel>> {
            override fun onResponse(call: Call<List<BanksResponseModel>>?, response: Response<List<BanksResponseModel>>?) {
                val result = response?.body()
                providersObservable.postValue(if (result != null) ObservableResult.Success(result)
                else ObservableResult.Error(Exception(Throwable())))
            }

            override fun onFailure(call: Call<List<BanksResponseModel>>?, t: Throwable?) {
                providersObservable.postValue(ObservableResult.Error(Exception(t)))
            }
        })
        return providersObservable
    }
}