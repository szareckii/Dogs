package com.zareckii.dogs.data.network

import com.zareckii.dogs.data.network.Result.Success
import com.zareckii.dogs.data.network.model.ErrorMessage

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class ErrorResponse(val errorMessage: ErrorMessage?) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is ErrorResponse -> "ErrorResponse[exception=${errorMessage?.message}]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.success
    get() = this is Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

val <T> Result<T>.data: T?
    get() = (this as? Success)?.data