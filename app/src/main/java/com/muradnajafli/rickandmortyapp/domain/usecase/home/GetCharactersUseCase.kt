package com.muradnajafli.rickandmortyapp.domain.usecase.home

import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.repository.remote.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import com.muradnajafli.rickandmortyapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetCharactersUseCaseImpl @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : GetCharactersUseCase {

    override suspend fun invoke(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    ): Flow<Result<List<RickAndMortyModel>>> {
        return withContext(Dispatchers.IO) {
            rickAndMortyRepository.getCharacters(name, status, species, gender)
        }
    }

}

fun interface GetCharactersUseCase {
    suspend operator fun invoke(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    ): Flow<Result<List<RickAndMortyModel>>>
}
