package com.mobile.shopaid.api.service

import com.mobile.shopaid.api.ApiConstants
import com.mobile.shopaid.data.response.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @get:GET(ApiConstants.USERS_URL)
    val user: Call<List<UserResponseModel>>
}