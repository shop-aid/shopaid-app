package com.mobile.shopaid.data.observable

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 18:08
 */

sealed class ObservableResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ObservableResult<T>()
    data class Error(val exception: Exception) : ObservableResult<Nothing>()
}