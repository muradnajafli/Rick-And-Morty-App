package com.muradnajafli.rickandmortyapp.data.dto


data class RickAndMortyResponse(
    val id: Int?,
    val gender: String?,
    val image: String?,
    val name: String?,
    val originResponse: OriginResponse?,
    val species: String?,
    val status: String?,
    val type: String?
)