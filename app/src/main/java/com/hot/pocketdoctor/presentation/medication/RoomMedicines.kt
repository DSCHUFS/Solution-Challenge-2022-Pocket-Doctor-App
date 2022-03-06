package com.hot.pocketdoctor.presentation.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek

@Entity(tableName = "room_medicines")
class RoomMedicines {
    @PrimaryKey
    @ColumnInfo
    var medicineName: String = ""
    @ColumnInfo
    var medicineReservationDay: String? = "" //요일
    @ColumnInfo
    var medicationReservationTime: String? = "" //시간
    @ColumnInfo
    var medicineInfo: String? = ""
}
