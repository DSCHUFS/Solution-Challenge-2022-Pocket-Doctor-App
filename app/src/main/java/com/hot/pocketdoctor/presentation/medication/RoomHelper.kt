package com.hot.pocketdoctor.presentation.medication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomMedicine::class], version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMedicineDAO(): RoomMedicineDAO

    companion object {
        private var INSTANCE: RoomHelper? =null

        fun getInstance(context: Context): RoomHelper? {
            if(INSTANCE == null){
                synchronized(RoomHelper::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    RoomHelper::class.java, "roomMedicineDB")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return  INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}