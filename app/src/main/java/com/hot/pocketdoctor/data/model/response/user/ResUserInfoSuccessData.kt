package com.hot.pocketdoctor.data.model.response.user

import com.google.gson.annotations.SerializedName

data class ResUserInfoSuccessData(
    @SerializedName("no")
    val userNo: Int,

    @SerializedName("email")
    val userEmail: String,

    @SerializedName("name")
    val userName: String,

    @SerializedName("phone_number")
    val userPhoneNum: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
