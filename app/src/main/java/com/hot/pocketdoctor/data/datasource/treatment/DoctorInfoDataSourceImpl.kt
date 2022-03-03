package com.hot.pocketdoctor.data.datasource.treatment

import com.hot.pocketdoctor.data.api.TreatmentService
import com.hot.pocketdoctor.data.model.response.treatment.ResDoctorInfoSuccessData

class DoctorInfoDataSourceImpl(val treatmentService: TreatmentService) : DoctorInfoDataSource {
    override suspend fun fetchDoctorInfo(): ResDoctorInfoSuccessData {
        return treatmentService.fetchDoctorInfo()
    }
}