package com.muradnajafli.rickandmortyapp.domain.usecase.details

import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.repository.local.SavedRickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetCharacterByIdFromSavedImpl @Inject constructor(
    private val savedRickAndMortyRepository: SavedRickAndMortyRepository
) : GetCharacterByIdFromSaved{

    override suspend fun invoke(characterId: Int): RickAndMortyModel? {
        return withContext(Dispatchers.IO) {
            savedRickAndMortyRepository.getCharacterById(characterId)
        }
    }

}

fun interface GetCharacterByIdFromSaved {
    suspend operator fun invoke(characterId: Int): RickAndMortyModel?
}