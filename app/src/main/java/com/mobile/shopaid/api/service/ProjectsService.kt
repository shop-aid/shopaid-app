package com.mobile.shopaid.api.service

import com.mobile.shopaid.api.ApiConstants
import com.mobile.shopaid.data.response.ProjectsResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 10:54
 */

interface ProjectsService {

    @get:GET(ApiConstants.PROJECTS_URL)
    val projects: Call<List<ProjectsResponseModel>>
}