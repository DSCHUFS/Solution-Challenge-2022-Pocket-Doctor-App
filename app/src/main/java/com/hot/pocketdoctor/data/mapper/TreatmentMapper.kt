package com.hot.pocketdoctor.data.mapper

import com.hot.pocketdoctor.data.model.response.info.ResDoctorInfoSuccessData
import com.hot.pocketdoctor.data.model.response.info.ResHospitalInfoSuccessData
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData
import com.hot.pocketdoctor.domain.model.info.HospitalDetailData

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

    fun mapperToHospitalDetailData(resHospitalInfoSuccessData: ResHospitalInfoSuccessData) : HospitalDetailData {
        return HospitalDetailData(
            hospitalNo = resHospitalInfoSuccessData.hospitalNo,
            hospitalHomePage = resHospitalInfoSuccessData.hospitalHomepage,
            hospitalLocation = resHospitalInfoSuccessData.hospitalLocation,
            hospitalTime = resHospitalInfoSuccessData.hospitalTime
        )
    }
}