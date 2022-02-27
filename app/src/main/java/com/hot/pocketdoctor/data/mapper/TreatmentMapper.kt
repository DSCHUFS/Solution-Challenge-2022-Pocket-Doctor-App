package com.hot.pocketdoctor.data.mapper

import com.hot.pocketdoctor.data.model.response.treatment.ResDoctorInfoSuccessData
import com.hot.pocketdoctor.data.model.response.treatment.ResHospitalInfoSuccessData
import com.hot.pocketdoctor.data.model.response.treatment.ResReservationSuccessData
import com.hot.pocketdoctor.domain.model.treatment.DoctorInfoData
import com.hot.pocketdoctor.domain.model.treatment.HospitalDetailData
import com.hot.pocketdoctor.domain.model.treatment.ReservationData
import com.hot.pocketdoctor.util.SubjectUtils

object TreatmentMapper {

    fun mapperToDoctorInfoData(resDoctorInfoSuccessData: ResDoctorInfoSuccessData) : DoctorInfoData {
        return DoctorInfoData(
            result = resDoctorInfoSuccessData.result.map { data ->
                DoctorInfoData.DoctorInfo(
                    doctorNo = data.doctorNo,
                    doctorName = data.doctorName,
                    hospitalName = data.hospitalName,
                    subject = SubjectUtils.convertSubjectWithHashTag(data.subject),
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

    fun mapperToReservationData(resReservationSuccessData: ResReservationSuccessData) : ReservationData {
        return ReservationData(
            message = resReservationSuccessData.message,
            status = resReservationSuccessData.status
        )
    }
}