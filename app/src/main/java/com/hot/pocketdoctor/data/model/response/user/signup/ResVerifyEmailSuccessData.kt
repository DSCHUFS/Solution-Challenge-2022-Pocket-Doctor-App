package com.hot.pocketdoctor.data.model.response.user.signup

import com.google.gson.annotations.SerializedName

data class ResVerifyEmailSuccessData(
    @SerializedName("auth_num")
    val verificationCode: Int,

    @SerializedName("email")
    val verifiedEmail: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
