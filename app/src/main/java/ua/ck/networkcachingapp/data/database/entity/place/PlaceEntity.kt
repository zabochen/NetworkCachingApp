package ua.ck.networkcachingapp.data.database.entity.place

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.ck.networkcachingapp.data.database.config.DatabaseConfig

@Entity(tableName = DatabaseConfig.TABLE_PLACES_NAME)
data class PlaceEntity(
    @PrimaryKey val id: Int,

    // Title
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "title_en") val titleEn: String,

    // Description
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "description_en") val descriptionEn: String,

    // Category
    @ColumnInfo(name = "category_id") val categoryId: Int,

    // Coordinates
    @Embedded(prefix = "coordinates_") val coordinates: CoordinatesEntity,

    // Photo
    @ColumnInfo(name = "photo_url") val photoUrl: String,
    @ColumnInfo(name = "photo_url_local") val photoUrlLocal: String
)