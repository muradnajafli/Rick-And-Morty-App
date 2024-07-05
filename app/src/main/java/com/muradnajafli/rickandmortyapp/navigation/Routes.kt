package com.muradnajafli.rickandmortyapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Details(val id: Int)