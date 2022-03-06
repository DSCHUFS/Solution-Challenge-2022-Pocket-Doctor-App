package com.hot.pocketdoctor.data.repository.treatment

import com.hot.pocketdoctor.data.datasource.treatment.DoctorInfoDataSource
import com.hot.pocketdoctor.data.mapper.TreatmentMapper
import com.hot.pocketdoctor.domain.model.treatment.DoctorInfoData
import com.hot.pocketdoctor.domain.repository.DoctorInfoRepository

class DoctorInfoRepositoryImpl(private val doctorInfoDataSource: DoctorInfoDataSource) : DoctorInfoRepository {
    override suspend fun fetchDoctorInfo(): DoctorInfoData {
        return TreatmentMapper.mapperToDoctorInfoData(doctorInfoDataSource.fetchDoctorInfo())
    }
}