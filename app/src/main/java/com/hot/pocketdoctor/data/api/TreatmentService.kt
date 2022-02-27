package com.hot.pocketdoctor.data.api

import com.hot.pocketdoctor.data.model.request.reservation.ReqReservationSuccessData
import com.hot.pocketdoctor.data.model.response.treatment.ResDoctorInfoSuccessData
import com.hot.pocketdoctor.data.model.response.treatment.ResHospitalInfoSuccessData
import com.hot.pocketdoctor.data.model.response.treatment.ResReservationSuccessData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TreatmentService {

    @GET("/home/doctor")
    suspend fun fetchDoctorInfo() : ResDoctorInfoSuccessData

    @GET("/home/hospital")
    suspend fun fetchHospitalInfo(
        @Query("doctor_no") doctorNo : Int
    ) : ResHospitalInfoSuccessData

    @POST("/home/reservation")
    suspend fun postReservation(
        @Body body: ReqReservationSuccessData
    ) : ResReservationSuccessData
}