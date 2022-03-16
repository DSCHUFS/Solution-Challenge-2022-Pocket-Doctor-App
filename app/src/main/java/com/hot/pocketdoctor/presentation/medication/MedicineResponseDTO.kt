package com.hot.pocketdoctor.presentation.medication

import com.google.gson.annotations.SerializedName

data class MedicineResponseDTO(
    @SerializedName("medicine_info") val medicineInfo : String,
    @SerializedName("message") val responseMessage : String,
    @SerializedName("status") val responseStatus : Int,
)