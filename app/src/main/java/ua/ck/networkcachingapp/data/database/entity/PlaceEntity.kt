package ua.ck.networkcachingapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.ck.networkcachingapp.data.database.config.DatabaseConfig

@Entity(tableName = DatabaseConfig.TABLE_PLACES_NAME)
data class PlaceEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
)