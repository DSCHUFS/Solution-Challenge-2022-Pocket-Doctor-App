package com.hot.pocketdoctor.data.model.request.signup

import com.google.gson.annotations.SerializedName

data class ReqSignUpSuccessData(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("phone_number")
    val phoneNumber: String
)
