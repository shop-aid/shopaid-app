package com.mobile.shopaid.api.client

import com.mobile.shopaid.api.ApiConstants
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 17:49
 */

object RetrofitClient {

    val retrofit: Retrofit
        get() = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .baseUrl(baseUrl)
                .client(OkHttpClient.Builder().build())
                .build()

    private val baseUrl: HttpUrl
        get() = HttpUrl.Builder()
                .scheme(ApiConstants.URL_SCHEME)
                .host(ApiConstants.BASE_URL)
                .build()
}