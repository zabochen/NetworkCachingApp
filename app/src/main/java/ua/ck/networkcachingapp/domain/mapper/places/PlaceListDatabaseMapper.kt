package ua.ck.networkcachingapp.domain.mapper.places

import ua.ck.networkcachingapp.data.database.entity.PlaceEntity
import ua.ck.networkcachingapp.domain.mapper.Mapper
import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse

class PlaceListDatabaseMapper :
    Mapper<List<PlaceResponse>, List<PlaceEntity>> {

    override fun map(data: List<PlaceResponse>): List<PlaceEntity> {
        return data.map {
            PlaceEntity(
                id = it.id,
                title = it.title,
                description = it.description
            )
        }
    }
}