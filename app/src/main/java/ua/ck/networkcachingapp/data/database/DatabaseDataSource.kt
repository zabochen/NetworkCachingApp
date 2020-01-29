package ua.ck.networkcachingapp.data.database

import ua.ck.networkcachingapp.domain.model.ui.places.Place

interface DatabaseDataSource {
    fun savePlaceList(placeList: List<Place>)
}