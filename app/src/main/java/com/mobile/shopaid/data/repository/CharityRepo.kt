package com.mobile.shopaid.data.repository

import android.arch.lifecycle.LiveData
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.response.CauseResponseModel
import com.mobile.shopaid.data.response.ProjectsResponseModel

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:01
 */

interface CharityRepo {

    fun fetchCauses(): LiveData<ObservableResult<List<CauseResponseModel>>>

    fun fetchProjects(): LiveData<ObservableResult<List<ProjectsResponseModel>>>
}