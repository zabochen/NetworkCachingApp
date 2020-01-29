package ua.ck.networkcachingapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ua.ck.networkcachingapp.data.database.config.DatabaseConfig
import ua.ck.networkcachingapp.data.database.entity.PlaceEntity

@Dao
interface PlaceDao {

    @Query("SELECT * FROM ${DatabaseConfig.TABLE_PLACES_NAME}")
    fun getAllPlace(): List<PlaceEntity>

    @Insert
    fun addPlace(placeEntity: PlaceEntity)

    @Insert
    fun addAllPlaces(placeList: List<PlaceEntity>)
}