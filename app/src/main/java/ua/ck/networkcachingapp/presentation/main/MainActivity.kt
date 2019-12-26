package ua.ck.networkcachingapp.presentation.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ua.ck.networkcachingapp.R
import ua.ck.networkcachingapp.domain.model.places.PlaceResponse
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://travelche.everlabs.com/"

    private val mainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModelSubscribers()
        getData()
    }


    private fun initUi() {
        // Layout
        setContentView(R.layout.activity_main)
    }

    private fun initViewModelSubscribers() {

        // Places
        this.mainViewModel.places
            .observe(this, Observer { placeList ->
                // Image Caching
                cacheFiles(placeList = placeList)
            })

        // Places: Error
        this.mainViewModel.placesError
            .observe(this, Observer {
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG)
                    .show()
            })
    }

    private fun getData() {
        // Get Places
        this.mainViewModel.getPlaces()
    }

    private fun cacheFiles(placeList: List<PlaceResponse>) {

        // App Path
        val appPath = this.filesDir

        try {
            val filePath = File(appPath, "images")

            // Create folder
            if (!filePath.exists()) {
                filePath.mkdir()
            }

            placeList.forEach { place ->

                Log.i("MainActivity", "${place.title}")

                val image = File(filePath, "${place.title}.jpg")

                val fileOuStream = FileOutputStream(image)

                val url = URL(place.photoUrl)
                val bitmapImage = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, 85, fileOuStream)

                fileOuStream.flush()
                fileOuStream.close()
            }


        } catch (e: Exception) {

            Log.i("MainActivity", "Exception")

        }
    }
}
