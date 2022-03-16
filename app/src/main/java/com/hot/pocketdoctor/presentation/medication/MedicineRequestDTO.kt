package com.hot.pocketdoctor.presentation.medication

import com.google.gson.annotations.SerializedName

data class MedicineRequestDTO (
    @SerializedName("medicine_name") val medicineName : String,
)