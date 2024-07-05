package com.muradnajafli.rickandmortyapp.domain.repository.local

import com.muradnajafli.rickandmortyapp.data.entity.RickAndMortyEntity
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import kotlinx.coroutines.flow.Flow

interface SavedRickAndMortyRepository {
    suspend fun getSavedCharacters(): Flow<List<RickAndMortyModel>>
    suspend fun addCharacter(rickAndMortyEntity: RickAndMortyEntity)
    suspend fun deleteCharacter(rickAndMortyEntity: RickAndMortyEntity)
    suspend fun getCharacterById(characterId: Int): RickAndMortyModel?
}