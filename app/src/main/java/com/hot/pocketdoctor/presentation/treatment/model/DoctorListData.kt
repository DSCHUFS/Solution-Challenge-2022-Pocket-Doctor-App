package com.hot.pocketdoctor.presentation.treatment.model

data class DoctorListData(
    val doctorNo: Int,
    val doctorName: String,
    val hospitalName: String,
    val subject: String,
    val availability: String
)
