package com.muradnajafli.rickandmortyapp.data.remote

import com.muradnajafli.rickandmortyapp.data.dto.RickAndMortyResponse
import com.muradnajafli.rickandmortyapp.data.dto.RickAndMortyListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
       @Query("name") name: String? = null,
       @Query("status") status: String? = null,
       @Query("species") species: String? = null,
       @Query("gender") gender: String? = null
    ): Response<RickAndMortyListResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<RickAndMortyResponse>

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

}