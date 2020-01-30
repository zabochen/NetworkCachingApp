package ua.ck.networkcachingapp.data.network.service

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import ua.ck.networkcachingapp.domain.model.network.places.PlaceResponse
import java.util.concurrent.TimeUnit

interface NetworkService {

    companion object {

        private const val baseUrl = "https://travelche.everlabs.com/api/"

        operator fun invoke(): NetworkService {
            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkService::class.java)
        }
    }

    @GET("v1/places")
    suspend fun getPlaces(): Response<List<PlaceResponse>>

    @GET
    fun getImage(@Url imageUrl: String): Call<ResponseBody>

}