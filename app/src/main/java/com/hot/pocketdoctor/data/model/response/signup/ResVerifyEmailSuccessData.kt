package com.hot.pocketdoctor.data.model.response.signup

import com.google.gson.annotations.SerializedName

data class ResVerifyEmailSuccessData(
    @SerializedName("auth_num")
    val verificationCode: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
