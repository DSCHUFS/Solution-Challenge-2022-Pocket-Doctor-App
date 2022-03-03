package com.hot.pocketdoctor.data.repository.treatment

import com.hot.pocketdoctor.data.datasource.treatment.HospitalInfoDataSource
import com.hot.pocketdoctor.data.mapper.TreatmentMapper
import com.hot.pocketdoctor.domain.model.treatment.HospitalDetailData
import com.hot.pocketdoctor.domain.repository.HospitalInfoRepository

class HospitalInfoRepositoryImpl(private val hospitalInfoDataSource: HospitalInfoDataSource) : HospitalInfoRepository {
    override suspend fun fetchHospitalInfo(doctorNo: Int): HospitalDetailData {
        return TreatmentMapper.mapperToHospitalDetailData(hospitalInfoDataSource.fetchHospitalInfo(doctorNo))
    }
}