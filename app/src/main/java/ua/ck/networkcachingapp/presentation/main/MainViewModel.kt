package ua.ck.networkcachingapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.ck.networkcachingapp.data.network.service.NetworkService
import ua.ck.networkcachingapp.domain.model.places.PlaceResponse

class MainViewModel : ViewModel() {

    private var _places = MutableLiveData<List<PlaceResponse>>()
    val places: LiveData<List<PlaceResponse>> = _places

    private var _placesError = MutableLiveData<Unit>()
    val placesError: LiveData<Unit> = _placesError

    fun getPlaces() {
        viewModelScope.launch {
            val placeListResponse = withContext(Dispatchers.IO) {
                return@withContext NetworkService.invoke().getPlaces().body()
            }

            if (!placeListResponse.isNullOrEmpty()) {
                _places.value = placeListResponse
            } else {
                _placesError.value = Unit
            }
        }
    }

}