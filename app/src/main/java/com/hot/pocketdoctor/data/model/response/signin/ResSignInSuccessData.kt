package com.hot.pocketdoctor.data.model.response.signin

import com.google.gson.annotations.SerializedName

data class ResSignInSuccessData(
    @SerializedName("token")
    val token: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
