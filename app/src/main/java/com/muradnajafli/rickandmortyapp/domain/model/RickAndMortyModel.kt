package com.muradnajafli.rickandmortyapp.domain.model


data class RickAndMortyModel(
    val id: Int,
    val gender: String,
    val image: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
    val isSaved: Boolean
)
