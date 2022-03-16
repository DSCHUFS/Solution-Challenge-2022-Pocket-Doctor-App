package com.hot.pocketdoctor.presentation.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomMedicineDAO {

    @Query("select * from RoomMedicine")
    fun getAll(): List<RoomMedicine>

    @Insert(onConflict = 1) //replace
    fun insert(medicine: RoomMedicine)

    @Delete
    fun delete(medicine: RoomMedicine)
}