package com.mobile.shopaid.ui.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 20:16
 */

class ViewModelFactory<out V : ViewModel>(private val mViewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mViewModel as T
    }
}