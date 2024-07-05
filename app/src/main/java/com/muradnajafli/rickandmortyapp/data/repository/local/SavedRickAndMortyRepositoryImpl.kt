package com.muradnajafli.rickandmortyapp.data.repository.local

import com.muradnajafli.rickandmortyapp.data.database.RickAndMortyDao
import com.muradnajafli.rickandmortyapp.data.entity.RickAndMortyEntity
import com.muradnajafli.rickandmortyapp.data.mapper.toRickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.repository.local.SavedRickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SavedRickAndMortyRepositoryImpl @Inject constructor(
    private val savedRickAndMortyDao: RickAndMortyDao
) : SavedRickAndMortyRepository {

    override suspend fun getSavedCharacters(): Flow<List<RickAndMortyModel>> {
        return savedRickAndMortyDao.getSavedCharacters().map { entities ->
            entities.map { it.toRickAndMortyModel() }
        }
    }

    override suspend fun getCharacterById(characterId: Int): RickAndMortyModel? {
        return savedRickAndMortyDao.getCharacterById(characterId)?.toRickAndMortyModel()
    }

    override suspend fun addCharacter(rickAndMortyEntity: RickAndMortyEntity) {
        savedRickAndMortyDao.insert(rickAndMortyEntity)
    }

    override suspend fun deleteCharacter(rickAndMortyEntity: RickAndMortyEntity) {
        savedRickAndMortyDao.delete(rickAndMortyEntity)
    }

}