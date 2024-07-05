package com.muradnajafli.rickandmortyapp.data.repository.remote

import com.muradnajafli.rickandmortyapp.data.mapper.toRickAndMortyList
import com.muradnajafli.rickandmortyapp.data.mapper.toRickAndMortyModel
import com.muradnajafli.rickandmortyapp.data.remote.RickAndMortyApi
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.repository.remote.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.muradnajafli.rickandmortyapp.utils.Result
import retrofit2.Response


class RickAndMortyRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) : RickAndMortyRepository {

    override suspend fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    ): Flow<Result<List<RickAndMortyModel>>> {
        return makeApiCall({
            rickAndMortyApi.getCharacters(
                name = name,
                status = status,
                species = species,
                gender = gender
            )
        }, { it.toRickAndMortyList() })
    }

    override suspend fun getCharacterById(id: Int): Flow<Result<RickAndMortyModel>> {
        return makeApiCall({
            rickAndMortyApi.getCharacterById(id)
        }, { it.toRickAndMortyModel() })
    }

    private suspend fun <T, R> makeApiCall(
        call: suspend () -> Response<T>,
        transform: (T) -> R
    ): Flow<Result<R>> {
        return flow {
            try {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(Result.Success(transform(body)))
                    } else {
                        emit(Result.Error("Response body is null"))
                    }
                } else {
                    emit(Result.Error("Response was not successful: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                emit(Result.Error("Error making API call: ${e.message ?: e.toString()}"))
            }
        }
    }
}