package com.muradnajafli.rickandmortyapp.domain.repository.remote

import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun getCharacters(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        gender: String? = null
    ): Flow<Result<List<RickAndMortyModel>>>

    suspend fun getCharacterById(id: Int): Flow<Result<RickAndMortyModel>>

}