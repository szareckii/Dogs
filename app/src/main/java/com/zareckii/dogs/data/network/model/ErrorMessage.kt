package com.zareckii.dogs.data.network.model

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    val httpException: Int,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
