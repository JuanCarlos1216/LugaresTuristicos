package com.juanca.exploreit.data.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.juanca.exploreit.models.Places

@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlaces(places: Places): Long
}