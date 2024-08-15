package com.juanca.exploreit.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanca.exploreit.data.Daos.PlacesDao
import com.juanca.exploreit.models.Places

@Database(
    entities = [Places::class],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun placesDao(): PlacesDao

    companion object {

        var instance: AppDataBase? = null

        fun getInstanceDB(context: Context): AppDataBase {
            if (instance == null) {
                //Create
                instance = Room.databaseBuilder(
                    context, AppDataBase::class.java, "BD_Places"
                ).build()
            }
            return instance as AppDataBase
        }
    }
}