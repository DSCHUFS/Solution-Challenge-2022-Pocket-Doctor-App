package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.domain.model.info.DoctorInfoData

interface DoctorInfoRepository {
    suspend fun fetchDoctorInfo() : DoctorInfoData
}