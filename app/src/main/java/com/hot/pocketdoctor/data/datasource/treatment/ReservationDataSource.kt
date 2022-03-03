package com.hot.pocketdoctor.data.datasource.treatment

import com.hot.pocketdoctor.data.model.request.reservation.ReqReservationSuccessData
import com.hot.pocketdoctor.data.model.response.treatment.ResReservationSuccessData

interface ReservationDataSource {
    suspend fun postReservation(body: ReqReservationSuccessData) : ResReservationSuccessData
}