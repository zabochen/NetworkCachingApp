package ua.ck.networkcachingapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ua.ck.networkcachingapp.R
import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse
import ua.ck.networkcachingapp.domain.state.PlaceListState
import ua.ck.networkcachingapp.presentation.internal.Extensions

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModelSubscribers()
        getPlaceList()
    }

    private fun initUi() {
        // Layout
        setContentView(R.layout.activity_main)
    }

    private fun initViewModelSubscribers() {

        // Place List: Status
        this.mainViewModel.placeListState
            .observe(this, Observer { placeListState ->
                when (placeListState) {
                    is PlaceListState.Success -> {
                        savePlaceListToDatabase(
                            placeList = placeListState.placeListResponse
                        )
                    }
                    is PlaceListState.Error -> {
                        showMessage(message = "PlaceListState => Error")
                    }
                }
            })
    }

    // Get "Place List"
    private fun getPlaceList() {
        this.mainViewModel.getPlaces()
    }

    // Save PlaceList to Database
    private fun savePlaceListToDatabase(placeList: List<PlaceResponse>) {
        this.mainViewModel.savePlaceListToDatabase(this, placeList)
    }

    private fun showMessage(message: String) {
        Extensions.showSnackbar(
            view = activityMain_constraintLayout_parent,
            message = message
        )
    }
}
