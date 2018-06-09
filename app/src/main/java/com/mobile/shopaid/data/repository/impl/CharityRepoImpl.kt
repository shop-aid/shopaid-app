package com.mobile.shopaid.data.repository.impl

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mobile.shopaid.api.client.RetrofitClient
import com.mobile.shopaid.api.service.CausesService
import com.mobile.shopaid.api.service.ProjectsService
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.CharityRepo
import com.mobile.shopaid.data.response.ProjectsResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:02
 */

class CharityRepoImpl : CharityRepo {

    private val causesService = RetrofitClient.retrofit.create(CausesService::class.java)
    private val projectsService = RetrofitClient.retrofit.create(ProjectsService::class.java)
    private val causesObservable by lazy {
        MutableLiveData<ObservableResult<List<CauseResponseModel>>>()
    }
    private val projectsObservable by lazy {
        MutableLiveData<ObservableResult<List<ProjectsResponseModel>>>()
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

    override fun fetchProjects(): LiveData<ObservableResult<List<ProjectsResponseModel>>> {
        projectsService.projects.enqueue(object : Callback<List<ProjectsResponseModel>> {
            override fun onResponse(call: Call<List<ProjectsResponseModel>>?, response: Response<List<ProjectsResponseModel>>?) {
                val result = response?.body()
                projectsObservable.postValue(if (result != null) ObservableResult.Success(result)
                else ObservableResult.Error(Exception(Throwable())))
            }

            override fun onFailure(call: Call<List<ProjectsResponseModel>>?, t: Throwable?) {
                projectsObservable.postValue(ObservableResult.Error(Exception(t)))
            }
        })
        return projectsObservable
    }
}