package ua.ck.networkcachingapp.domain.repository

import ua.ck.networkcachingapp.domain.model.ui.places.Place

interface Repository {
    fun savePlaceList(placeList: List<Place>)
}