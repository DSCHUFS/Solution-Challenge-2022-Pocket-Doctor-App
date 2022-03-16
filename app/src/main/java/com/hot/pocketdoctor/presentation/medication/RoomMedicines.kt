package com.hot.pocketdoctor.presentation.medication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomMedicine(

    val medicineName: String?, //약이름
    val medicineReservationDay: Int?, //주 몇회
    val medicineReservationTime: String, //알림 설정 시간( 최대 3개 )
    val medicineInfo: String? //약정보
){
    @PrimaryKey(autoGenerate = true) var medicineNo: Int =0
}


