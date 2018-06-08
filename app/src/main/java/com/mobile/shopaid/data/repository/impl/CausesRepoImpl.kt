package com.mobile.shopaid.data.repository.impl

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mobile.shopaid.api.client.RetrofitClient
import com.mobile.shopaid.api.service.CausesService
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.CausesRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:02
 */

class CausesRepoImpl : CausesRepo {

    private val causesService = RetrofitClient.retrofit.create(CausesService::class.java)
    private val causesObservable by lazy {
        MutableLiveData<ObservableResult<List<CauseResponseModel>>>()
    }

    override fun fetchCauses(): LiveData<ObservableResult<List<CauseResponseModel>>> {
        causesService.causes.enqueue(object : Callback<List<CauseResponseModel>> {
            override fun onResponse(call: Call<List<CauseResponseModel>>?, response: Response<List<CauseResponseModel>>?) {
                val result = response?.body()
                causesObservable.postValue(if (result != null) ObservableResult.Success(result)
                else ObservableResult.Error(Exception(Throwable())))
            }

            override fun onFailure(call: Call<List<CauseResponseModel>>?, t: Throwable?) {
                causesObservable.postValue(ObservableResult.Error(Exception(t)))
            }
        })
        return causesObservable
    }
}