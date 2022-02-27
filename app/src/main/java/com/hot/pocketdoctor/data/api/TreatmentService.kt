package com.hot.pocketdoctor.data.api

import com.hot.pocketdoctor.data.model.response.info.ResDoctorInfoSuccessData
import com.hot.pocketdoctor.data.model.response.info.ResHospitalInfoSuccessData
import retrofit2.http.GET
import retrofit2.http.Query

interface TreatmentService {

    @GET("/home/doctor")
    suspend fun fetchDoctorInfo() : ResDoctorInfoSuccessData

    @GET("/home/hospital")
    suspend fun fetchHospitalInfo(
        @Query("doctor_no") doctorNo : Int
    ) : ResHospitalInfoSuccessData
}