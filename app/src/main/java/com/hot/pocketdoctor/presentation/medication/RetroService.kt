package com.hot.pocketdoctor.presentation.medication


import com.hot.pocketdoctor.presentation.medication.MedicineResponseDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetroService {

    @FormUrlEncoded
    @POST("/medicine")
    fun postMedicine(@Body request:MedicineRequestDTO,
    ): Call<MedicineResponseDTO>
}

