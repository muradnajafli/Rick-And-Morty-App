package com.muradnajafli.rickandmortyapp.data.mapper

import com.muradnajafli.rickandmortyapp.data.dto.RickAndMortyResponse
import com.muradnajafli.rickandmortyapp.data.dto.RickAndMortyListResponse
import com.muradnajafli.rickandmortyapp.data.entity.RickAndMortyEntity
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel


fun RickAndMortyResponse.toRickAndMortyModel(): RickAndMortyModel {
    return RickAndMortyModel(
        id = id ?: -1,
        name = name.orEmpty(),
        status = status.orEmpty(),
        species = species.orEmpty(),
        type = type.orEmpty(),
        image = image.orEmpty(),
        gender = gender.orEmpty(),
        origin = originResponse?.name.orEmpty(),
        isSaved = false
    )
}

fun RickAndMortyListResponse.toRickAndMortyList(): List<RickAndMortyModel> {
    return results?.map { it.toRickAndMortyModel() } ?: emptyList()
}

fun RickAndMortyModel.toRickAndMortyEntity(): RickAndMortyEntity {
    return RickAndMortyEntity(
        id = id
    )
}

fun RickAndMortyEntity.toRickAndMortyModel(): RickAndMortyModel {
    return RickAndMortyModel(
        id = id,
        name = "",
        status = "",
        species = "",
        type = "",
        image = "",
        gender = "",
        origin = "",
        isSaved = true
    )
}
