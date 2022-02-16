package com.hot.pocketdoctor.data.mapper

import com.hot.pocketdoctor.data.model.response.info.ResDoctorInfoSuccessData
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData

object TreatmentMapper {

    fun mapperToDoctorInfoData(resDoctorInfoSuccessData: ResDoctorInfoSuccessData) : DoctorInfoData {
        return DoctorInfoData(
            result = resDoctorInfoSuccessData.result as List<DoctorInfoData.DoctorInfo>,
            message = resDoctorInfoSuccessData.message,
            status = resDoctorInfoSuccessData.status
        )
    }
}