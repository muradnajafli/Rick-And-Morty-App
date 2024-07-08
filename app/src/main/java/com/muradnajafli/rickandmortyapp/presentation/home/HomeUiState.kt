package com.muradnajafli.rickandmortyapp.presentation.home

import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.utils.UiText

data class HomeUiState(
    val characters: List<RickAndMortyModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)
