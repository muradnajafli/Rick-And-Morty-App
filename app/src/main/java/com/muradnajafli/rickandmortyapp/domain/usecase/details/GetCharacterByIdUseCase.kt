package com.muradnajafli.rickandmortyapp.domain.usecase.details

import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.repository.remote.RickAndMortyRepository
import com.muradnajafli.rickandmortyapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCharacterByIdUseCaseImpl @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : GetCharacterByIdUseCase {

    override suspend fun invoke(id: Int): Flow<Result<RickAndMortyModel>> {
        return withContext(Dispatchers.IO) {
            rickAndMortyRepository.getCharacterById(id)
        }
    }

}

fun interface GetCharacterByIdUseCase {
    suspend operator fun invoke(id: Int): Flow<Result<RickAndMortyModel>>
}