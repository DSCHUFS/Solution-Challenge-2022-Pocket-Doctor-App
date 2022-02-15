package com.hot.pocketdoctor.domain.model

import com.google.gson.annotations.SerializedName

data class SignInData(
    @SerializedName("token")
    val token: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
