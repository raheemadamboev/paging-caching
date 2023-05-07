package xyz.teamgravity.pagingcaching.domain.model

data class BeerModel(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String,
)
