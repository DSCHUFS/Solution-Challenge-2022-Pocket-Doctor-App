package com.hot.pocketdoctor.data.datasource.treatment

import com.hot.pocketdoctor.data.model.response.treatment.ResHospitalInfoSuccessData

interface HospitalInfoDataSource {
    suspend fun fetchHospitalInfo(doctorNo: Int) : ResHospitalInfoSuccessData
}