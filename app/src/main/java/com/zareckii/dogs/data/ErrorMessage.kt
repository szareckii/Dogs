package com.zareckii.dogs.data

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    val httpException: Int,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
