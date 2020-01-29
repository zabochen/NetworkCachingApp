package ua.ck.networkcachingapp.domain.model.network.places

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("address_en")
    val addressEn: String,
    @SerializedName("audio_url")
    val audioUrl: String,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("description")
    val description: String,
    @SerializedName("description_en")
    val descriptionEn: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("phones")
    val phones: List<String>,
    @SerializedName("photo_author")
    val photoAuthor: String,
    @SerializedName("photo_author_en")
    val photoAuthorEn: String,
    @SerializedName("photo_url")
    val photoUrl: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("website")
    val website: String
)