package com.mobile.shopaid.ui.viewmodel.impl

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.mobile.shopaid.data.observable.ObservableResult
import com.mobile.shopaid.data.repository.CharityRepo
import com.mobile.shopaid.data.repository.impl.CharityRepoImpl
import com.mobile.shopaid.data.response.ProjectsResponseModel
import com.mobile.shopaid.ui.viewmodel.ProjectsViewModelContract
import com.mobile.shopaid.ui.viewmodel.factory.ViewModelFactory

/**
 * Created by: Petar Anastasov
 * On: 09/06/2018
 * At: 10:58
 */

class ProjectsViewModel(private val charityRepo: CharityRepo) : ViewModel(), ProjectsViewModelContract {

    companion object {
        fun create(owner: Fragment): ProjectsViewModel {
            val factory = ViewModelFactory(ProjectsViewModel(CharityRepoImpl()))
            return ViewModelProviders.of(owner, factory).get(ProjectsViewModel::class.java)
        }
    }

    val projectsObservable by lazy {
        MediatorLiveData<ObservableResult<List<ProjectsResponseModel>>>()
    }

    val loadingObservable by lazy {
        MutableLiveData<Boolean>()
    }

    private fun fetchProjectsInternal() {
        val repoData = charityRepo.fetchProjects()
        projectsObservable.removeSource(repoData)
        projectsObservable.addSource(repoData) {
            loadingObservable.value = false
            projectsObservable.value = it
        }
    }

    override fun fetchProjects() {
        loadingObservable.value = true
        fetchProjectsInternal()
    }
}