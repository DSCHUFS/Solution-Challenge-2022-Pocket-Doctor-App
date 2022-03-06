package com.hot.pocketdoctor.presentation.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomMedicineDAO {

    @Query("select * from room_medicines")
    fun getAll(): List<RoomMedicines>

    @Insert(onConflict = 1) //replace
    fun insert(medicine: RoomMedicines)

    @Delete
    fun delete(medicine: RoomMedicines)
}