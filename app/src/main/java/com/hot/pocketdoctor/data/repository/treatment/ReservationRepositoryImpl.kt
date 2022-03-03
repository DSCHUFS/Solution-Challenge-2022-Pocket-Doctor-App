package com.hot.pocketdoctor.data.repository.treatment

import com.hot.pocketdoctor.data.datasource.treatment.ReservationDataSource
import com.hot.pocketdoctor.data.mapper.TreatmentMapper
import com.hot.pocketdoctor.data.model.request.reservation.ReqReservationSuccessData
import com.hot.pocketdoctor.domain.model.treatment.ReservationData
import com.hot.pocketdoctor.domain.repository.ReservationRepository

class ReservationRepositoryImpl(private val reservationDataSource: ReservationDataSource) : ReservationRepository {
    override suspend fun postReservation(body: ReqReservationSuccessData): ReservationData {
        return TreatmentMapper.mapperToReservationData(reservationDataSource.postReservation(body))
    }
}