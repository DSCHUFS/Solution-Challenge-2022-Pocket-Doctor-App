package com.hot.pocketdoctor.data.model.response.info

import com.google.gson.annotations.SerializedName

data class ResHospitalInfoSuccessData(
    @SerializedName("hospital_no")
    val hospitalNo: Int,

    @SerializedName("hospital_hompage")
    val hospitalHomepage: String,

    @SerializedName("hospital_time")
    val hospitalTime: String,

    @SerializedName("hospital_location")
    val hospitalLocation: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
