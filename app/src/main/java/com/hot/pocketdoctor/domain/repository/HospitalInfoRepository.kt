package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.domain.model.treatment.HospitalDetailData

interface HospitalInfoRepository {
    suspend fun fetchHospitalInfo(doctorNo: Int) : HospitalDetailData
}