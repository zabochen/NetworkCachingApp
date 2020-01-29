package ua.ck.networkcachingapp.domain.model.network.places

import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)