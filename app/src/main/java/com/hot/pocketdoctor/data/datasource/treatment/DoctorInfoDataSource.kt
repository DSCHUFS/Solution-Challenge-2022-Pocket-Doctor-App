package com.hot.pocketdoctor.data.datasource.treatment

import com.hot.pocketdoctor.data.model.response.info.ResDoctorInfoSuccessData

interface DoctorInfoDataSource {
    suspend fun fetchDoctorInfo() : ResDoctorInfoSuccessData
}