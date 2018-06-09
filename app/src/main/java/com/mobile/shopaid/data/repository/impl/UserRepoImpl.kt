package com.mobile.shopaid.data.repository.impl

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mobile.shopaid.api.client.RetrofitClient
import com.mobile.shopaid.api.service.UserService
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.UserRepo
import com.mobile.shopaid.data.response.UserResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:02
 */

class UserRepoImpl : UserRepo {

    private val userService = RetrofitClient.retrofit.create(UserService::class.java)
    private val userObservable by lazy {
        MutableLiveData<ObservableResult<UserResponseModel>>()
    }

    override fun fetchUser(): LiveData<ObservableResult<UserResponseModel>> {
        userService.user.enqueue(object : Callback<UserResponseModel> {
            override fun onResponse(call: Call<UserResponseModel>?, response: Response<UserResponseModel>?) {
                val result = response?.body()
                userObservable.postValue(if (result != null) ObservableResult.Success(result)
                else ObservableResult.Error(Exception(Throwable())))
            }

            override fun onFailure(call: Call<UserResponseModel>?, t: Throwable?) {
                userObservable.postValue(ObservableResult.Error(Exception(t)))
            }
        })
        return userObservable
    }
}