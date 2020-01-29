package ua.ck.networkcachingapp.domain.mapper.places

import ua.ck.networkcachingapp.domain.mapper.Mapper
import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse
import ua.ck.networkcachingapp.domain.model.ui.places.Place

class PlaceListUiMapper :
    Mapper<List<PlaceResponse>, List<Place>> {

    override fun map(data: List<PlaceResponse>): List<Place> {
        return data.map {
            Place(
                id = it.id,
                title = it.title,
                description = it.description
            )
        }
    }
}