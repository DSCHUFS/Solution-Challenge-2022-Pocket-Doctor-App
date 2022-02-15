package com.hot.pocketdoctor.data.model.request.signin

import com.google.gson.annotations.SerializedName

data class ReqSignInSuccessData(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
