package com.hot.pocketdoctor.domain.model.info

import com.google.gson.annotations.SerializedName

data class DoctorInfoData(
    @SerializedName("result")
    val result: List<DoctorInfo>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
) {
    data class DoctorInfo(
        @SerializedName("doctor_no")
        val doctorNo: Int,
        @SerializedName("doctor_name")
        val doctorName: String,
        @SerializedName("hospital_name")
        val hospitalName: String,
        @SerializedName("doctor_subject")
        val subject: String,
        @SerializedName("doctor_enabled")
        val availability: Boolean
    )
}

