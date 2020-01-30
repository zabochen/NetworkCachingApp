package ua.ck.networkcachingapp.data.database.entity.place

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class CoordinatesEntity(
    @ColumnInfo(name = "lat") val lat: String,
    @ColumnInfo(name = "lng") val lng: String
)