package com.hot.pocketdoctor.presentation.medication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomMedicines::class], version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMedicineDAO(): RoomMedicineDAO
}