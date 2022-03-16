package com.hot.pocketdoctor.presentation.reports


import com.hot.pocketdoctor.presentation.medication.MedicineRequestDTO
import com.hot.pocketdoctor.presentation.medication.MedicineResponseDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetroService {

    @GET("/history")
    fun getReports(
    ): Call<ReportsResponseDTO>

    @POST("/history/medicine")
    suspend fun postMedicine(@Body body: MedicineRequestDTO
    ): Response<MedicineResponseDTO>
}

