package com.mobile.shopaid.data.repository

import android.arch.lifecycle.LiveData
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.UserResponseModel

interface UserRepo {

    fun fetchUser(): LiveData<ObservableResult<List<UserResponseModel>>>

}