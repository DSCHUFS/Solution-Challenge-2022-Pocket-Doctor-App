package com.hot.pocketdoctor.data.model.request.reservation

import com.google.gson.annotations.SerializedName

data class ReqReservationSuccessData(
    @SerializedName("hospital_no")
    val hospitalNo: Int,

    @SerializedName("doctor_no")
    val doctorNo: Int,

    @SerializedName("start_datetime")
    val startTime: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("is_video")
    val isVideo: Boolean
)
