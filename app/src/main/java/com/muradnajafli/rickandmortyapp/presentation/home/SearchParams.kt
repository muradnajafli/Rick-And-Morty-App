package com.muradnajafli.rickandmortyapp.presentation.home

data class SearchParams(
    val queryText: String? = "",
    val filterParams: FilterParams = FilterParams(
        currentGender = null,
        currentSpecies = null,
        currentStatus = null
    )
)

data class FilterParams(
    val currentGender: String?,
    val currentSpecies: String?,
    val currentStatus: String?
)
