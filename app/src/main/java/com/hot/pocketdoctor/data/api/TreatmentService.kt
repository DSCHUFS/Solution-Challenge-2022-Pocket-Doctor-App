package com.hot.pocketdoctor.data.api

import com.hot.pocketdoctor.data.model.response.info.ResDoctorInfoSuccessData
import retrofit2.http.GET

interface TreatmentService {

    @GET("/home/doctor")
    suspend fun fetchDoctorInfo() : ResDoctorInfoSuccessData
}