package ua.ck.networkcachingapp.presentation.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.ck.networkcachingapp.R
import ua.ck.networkcachingapp.data.network.service.NetworkService
import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse
import ua.ck.networkcachingapp.domain.state.PlaceListState
import ua.ck.networkcachingapp.presentation.internal.Extensions

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModelSubscribers()
        getPlaceList()
        loadingImage()
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

    private fun showLoading() {
        activityMain_loading.visibility = View.VISIBLE
    }


    private fun loadingImage() {
        GlobalScope.launch(Dispatchers.IO) {

            val image =
                "https://hub.everlabs.com/assets/event-flairs/xvacation-2b6c400539a48af8c171210b6a22cc9d94483333e7ce936e7191d1f5703f7da1.png.pagespeed.ic._s22Opw6IY.jpg"

            NetworkService.invoke().getImage(
                imageUrl = image
            ).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Log.i("MainActivity", "response.isSuccessful")
                    } else {
                        Log.i("MainActivity", "onFailure - 1")

                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("MainActivity", "onFailure - 2")
                }
            }
            )
        }
    }

    private fun showMessage(message: String) {
        Extensions.showSnackbar(
            view = activityMain_constraintLayout_parent,
            message = message
        )
    }
}
