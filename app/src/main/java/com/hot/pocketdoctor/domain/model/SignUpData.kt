package com.hot.pocketdoctor.domain.model

import com.google.gson.annotations.SerializedName

data class SignUpData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val code: Int
)
