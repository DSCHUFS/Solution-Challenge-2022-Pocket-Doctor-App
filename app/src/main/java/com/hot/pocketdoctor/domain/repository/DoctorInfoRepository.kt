package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.domain.model.treatment.DoctorInfoData

interface DoctorInfoRepository {
    suspend fun fetchDoctorInfo() : DoctorInfoData
}