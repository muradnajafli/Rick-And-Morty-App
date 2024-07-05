package com.muradnajafli.rickandmortyapp.domain.usecase.details

import com.muradnajafli.rickandmortyapp.data.entity.RickAndMortyEntity
import com.muradnajafli.rickandmortyapp.domain.repository.local.SavedRickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteCharacterUseCaseImpl @Inject constructor(
    private val savedRickAndMortyRepository: SavedRickAndMortyRepository
) : DeleteCharacterUseCase {

    override suspend fun invoke(rickAndMortyEntity: RickAndMortyEntity) {
        withContext(Dispatchers.IO) {
            savedRickAndMortyRepository.deleteCharacter(rickAndMortyEntity)
        }
    }

}

fun interface DeleteCharacterUseCase {
    suspend operator fun invoke(rickAndMortyEntity: RickAndMortyEntity)
}