package com.hot.pocketdoctor.presentation.reports

import com.google.gson.annotations.SerializedName

data class ReportsResponseDTO(
    @SerializedName("result") val reports : List<ReportData>? = null,
    @SerializedName("message") val statusMessage : String,
    @SerializedName("status") val statusCode : Int,
) {
    data class ReportData(
        @SerializedName("reservation_no") val userNo : Int,
        @SerializedName("user_name") val userName : String,
        @SerializedName("doctor_name") val doctorName : String,
        @SerializedName("hospital_name") val hospitalName : String,
        @SerializedName("start_datetime") val reportTime : String,
        @SerializedName("description") val description : String,
    )
}

