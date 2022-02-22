package com.hot.pocketdoctor.data.mapper

import com.hot.pocketdoctor.data.model.response.info.ResDoctorInfoSuccessData
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData

object TreatmentMapper {

    fun mapperToDoctorInfoData(resDoctorInfoSuccessData: ResDoctorInfoSuccessData) : DoctorInfoData {
        return DoctorInfoData(
            result = resDoctorInfoSuccessData.result.map { data ->
                DoctorInfoData.DoctorInfo(
                    doctorNo = data.doctorNo,
                    doctorName = data.doctorName,
                    hospitalName = data.hospitalName,
                    subject = data.subject,
                    availability = when (data.availability) {
                        true -> "Available"
                        false -> "Not Available"
                    }
                )
            }
        )
    }
}