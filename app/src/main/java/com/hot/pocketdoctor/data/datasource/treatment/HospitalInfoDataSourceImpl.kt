package com.hot.pocketdoctor.data.datasource.treatment

import com.hot.pocketdoctor.data.api.TreatmentService
import com.hot.pocketdoctor.data.model.response.info.ResDoctorInfoSuccessData
import com.hot.pocketdoctor.data.model.response.info.ResHospitalInfoSuccessData

class HospitalInfoDataSourceImpl(val treatmentService: TreatmentService) : HospitalInfoDataSource {
    override suspend fun fetchHospitalInfo(doctorNo: Int): ResHospitalInfoSuccessData {
        return treatmentService.fetchHospitalInfo(doctorNo)
    }
}