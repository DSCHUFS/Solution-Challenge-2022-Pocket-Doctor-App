package com.hot.pocketdoctor.data.datasource.treatment

import com.hot.pocketdoctor.data.api.TreatmentService
import com.hot.pocketdoctor.data.model.response.treatment.ResHospitalInfoSuccessData

class HospitalInfoDataSourceImpl(val treatmentService: TreatmentService) : HospitalInfoDataSource {
    override suspend fun fetchHospitalInfo(doctorNo: Int): ResHospitalInfoSuccessData {
        return treatmentService.fetchHospitalInfo(doctorNo)
    }
}