package ua.ck.networkcachingapp.domain.mapper.places

import ua.ck.networkcachingapp.data.database.entity.place.CoordinatesEntity
import ua.ck.networkcachingapp.data.database.entity.place.PlaceEntity
import ua.ck.networkcachingapp.domain.mapper.Mapper
import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse

class PlaceListDatabaseMapper :
    Mapper<List<PlaceResponse>, List<PlaceEntity>> {

    override fun map(data: List<PlaceResponse>): List<PlaceEntity> {
        return data.map { place ->
            PlaceEntity(
                id = place.id,

                // Title
                title = place.title,
                titleEn = place.titleEn,

                // Description
                description = place.description,
                descriptionEn = place.descriptionEn,

                // Category
                categoryId = place.categoryId,

                // Coordinates
                coordinates = CoordinatesEntity(
                    lat = place.coordinates.lat,
                    lng = place.coordinates.lng
                ),

                // Photo
                photoUrl = place.photoUrl,
                photoUrlLocal = ""
            )
        }
    }
}