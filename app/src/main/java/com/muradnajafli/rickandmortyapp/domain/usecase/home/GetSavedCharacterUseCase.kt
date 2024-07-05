package com.muradnajafli.rickandmortyapp.domain.usecase.home

import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.repository.local.SavedRickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetSavedCharacterUseCaseImpl(
    private val savedRickAndMortyRepository: SavedRickAndMortyRepository
) : GetSavedCharacterUseCase {

    override suspend fun invoke(): Flow<List<RickAndMortyModel>> {
        return withContext(Dispatchers.IO) {
            savedRickAndMortyRepository.getSavedCharacters()
        }
    }

}

fun interface GetSavedCharacterUseCase {
    suspend operator fun invoke(): Flow<List<RickAndMortyModel>>
}