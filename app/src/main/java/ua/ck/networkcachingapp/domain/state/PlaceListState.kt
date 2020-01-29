package ua.ck.networkcachingapp.domain.state

import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse

sealed class PlaceListState {
    data class Success(val placeListResponse: List<PlaceResponse>) : PlaceListState()
    object Error : PlaceListState()
}