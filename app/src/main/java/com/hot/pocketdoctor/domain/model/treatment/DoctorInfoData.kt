package com.hot.pocketdoctor.domain.model.treatment


data class DoctorInfoData(
    val result: List<DoctorInfo>,
) {
    data class DoctorInfo(
        val doctorNo: Int,
        val doctorName: String,
        val hospitalName: String,
        val subject: String,
        val availability: String
    )
}