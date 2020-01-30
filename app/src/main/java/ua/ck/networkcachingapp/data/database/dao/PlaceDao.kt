package ua.ck.networkcachingapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.ck.networkcachingapp.data.database.config.DatabaseConfig
import ua.ck.networkcachingapp.data.database.entity.place.PlaceEntity

@Dao
interface PlaceDao {

    @Query("SELECT * FROM ${DatabaseConfig.TABLE_PLACES_NAME}")
    fun getAllPlace(): List<PlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlace(placeEntity: PlaceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllPlaces(placeList: List<PlaceEntity>)
}