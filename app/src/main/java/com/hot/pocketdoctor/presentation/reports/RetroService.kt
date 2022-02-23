package com.hot.pocketdoctor.presentation.reports


import com.hot.pocketdoctor.presentation.medication.MedicineResponseDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetroService {

    @GET("/history")
    fun getReports(
    ): Call<ReportsResponseDTO>
}

