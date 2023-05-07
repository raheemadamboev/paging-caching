package xyz.teamgravity.pagingcaching.data.mapper

import xyz.teamgravity.pagingcaching.data.local.beer.entitiy.BeerEntity
import xyz.teamgravity.pagingcaching.data.remote.beer.dto.BeerDto
import xyz.teamgravity.pagingcaching.domain.model.BeerModel

fun BeerDto.toEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}

fun BeerEntity.toModel(): BeerModel {
    return BeerModel(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}