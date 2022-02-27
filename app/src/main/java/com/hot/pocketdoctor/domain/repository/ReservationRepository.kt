package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.data.model.request.reservation.ReqReservationSuccessData
import com.hot.pocketdoctor.domain.model.treatment.ReservationData

interface ReservationRepository {
    suspend fun postReservation(body: ReqReservationSuccessData) : ReservationData
}