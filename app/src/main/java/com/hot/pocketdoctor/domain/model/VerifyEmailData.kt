package com.hot.pocketdoctor.domain.model

import com.google.gson.annotations.SerializedName

data class VerifyEmailData(
    val verificationCode: Int,
    val verifiedEmail: String
)
