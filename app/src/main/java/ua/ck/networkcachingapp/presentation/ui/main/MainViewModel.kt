package ua.ck.networkcachingapp.presentation.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.ck.networkcachingapp.data.database.db.AppDatabase
import ua.ck.networkcachingapp.data.network.service.NetworkService
import ua.ck.networkcachingapp.domain.mapper.places.PlaceListDatabaseMapper
import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse
import ua.ck.networkcachingapp.domain.state.PlaceListState
import ua.ck.networkcachingapp.presentation.internal.LiveEvent

class MainViewModel : ViewModel() {

    private var _placeListState = LiveEvent<PlaceListState>()
    val placeListState: LiveData<PlaceListState> = _placeListState

    fun getPlaces() {
        viewModelScope.launch {
            val placeListResponse = withContext(Dispatchers.IO) {
                return@withContext NetworkService.invoke().getPlaces().body()
            }

            if (!placeListResponse.isNullOrEmpty()) {
                _placeListState.value = PlaceListState.Success(
                    placeListResponse = placeListResponse
                )
            } else {
                _placeListState.value = PlaceListState.Error
            }
        }
    }

    fun savePlaceListToDatabase(context: Context, placeList: List<PlaceResponse>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                AppDatabase(context.applicationContext)
                    .placeDao().apply {
                        addAllPlaces(placeList = PlaceListDatabaseMapper().map(data = placeList))
                    }
            }
        }
    }
}