package com.hot.pocketdoctor.data.model.response.treatment

import com.google.gson.annotations.SerializedName

data class ResReservationSuccessData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
