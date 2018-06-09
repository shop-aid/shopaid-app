package com.mobile.shopaid.data.repository.impl

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mobile.shopaid.api.client.RetrofitClient
import com.mobile.shopaid.api.service.PartnerService
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.PartnerRepo
import com.mobile.shopaid.data.response.PartnerBreakdownResponseModel
import com.mobile.shopaid.data.response.PartnerResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:02
 */

class PartnerRepoImpl : PartnerRepo {

    private val partnerService = RetrofitClient.retrofit.create(PartnerService::class.java)
    private val partnerObservable by lazy {
        MutableLiveData<ObservableResult<List<PartnerResponseModel>>>()
    }

    override fun fetchPartners(): LiveData<ObservableResult<List<PartnerResponseModel>>> {
        partnerService.partners.enqueue(object : Callback<List<PartnerResponseModel>> {
            override fun onResponse(call: Call<List<PartnerResponseModel>>?, response: Response<List<PartnerResponseModel>>?) {
                val result = response?.body()
                partnerObservable.postValue(if (result != null) ObservableResult.Success(result)
                else ObservableResult.Error(Exception(Throwable())))
            }

            override fun onFailure(call: Call<List<PartnerResponseModel>>?, t: Throwable?) {
                partnerObservable.postValue(ObservableResult.Error(Exception(t)))
            }
        })
        return partnerObservable
    }
}