package com.hot.pocketdoctor.data.datasource.treatment

import com.hot.pocketdoctor.data.api.TreatmentService
import com.hot.pocketdoctor.data.model.request.reservation.ReqReservationSuccessData
import com.hot.pocketdoctor.data.model.response.treatment.ResReservationSuccessData

class ReservationDataSourceImpl(val treatmentService: TreatmentService) : ReservationDataSource {
    override suspend fun postReservation(body: ReqReservationSuccessData): ResReservationSuccessData {
        return treatmentService.postReservation(body)
    }
}
