package com.hot.pocketdoctor.presentation.reports


import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

    @GET("/history")
    fun getReports(
    ): Call<ReportsResponseDTO>
}

