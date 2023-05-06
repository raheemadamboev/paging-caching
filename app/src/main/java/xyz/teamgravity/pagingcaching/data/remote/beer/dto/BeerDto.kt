package xyz.teamgravity.pagingcaching.data.remote.beer.dto

import com.squareup.moshi.Json

data class BeerDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "description") val description: String,
    @Json(name = "first_brewed") val firstBrewed: String,
    @Json(name = "image_url") val imageUrl: String,
)
