package com.hot.pocketdoctor.data.model.request.signup

import com.google.gson.annotations.SerializedName

data class ReqVerifyEmailSuccessData(
    @SerializedName("email")
    val email: String
)
