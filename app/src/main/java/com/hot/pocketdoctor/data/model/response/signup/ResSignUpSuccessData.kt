package com.hot.pocketdoctor.data.model.response.signup

import com.google.gson.annotations.SerializedName

data class ResSignUpSuccessData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val code: Int
)
