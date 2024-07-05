package com.muradnajafli.rickandmortyapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.usecase.home.GetCharactersUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.home.GetSavedCharacterUseCase
import com.muradnajafli.rickandmortyapp.utils.Result
import com.muradnajafli.rickandmortyapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSavedCharacterUseCase: GetSavedCharacterUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow<List<RickAndMortyModel>>(emptyList())
    val characters = _characters.asStateFlow()

    private val _errorMessage = MutableStateFlow<UiText?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchParams = MutableStateFlow(SearchParams())
    val searchParameters = _searchParams.asStateFlow()

    fun onSearch(searchParams: SearchParams) {
        _searchParams.value = searchParams
        fetchCharacters()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchCharacters() {
        viewModelScope.launch {
            searchParameters
                .flatMapLatest { searchParams ->
                    getCharactersUseCase(
                        name = searchParams.queryText,
                        status = searchParams.filterParams.currentStatus,
                        species = searchParams.filterParams.currentSpecies,
                        gender = searchParams.filterParams.currentGender
                    )
                }
                .combineWithSavedCharacters()
                .collectResult()
        }
    }

    private suspend fun Flow<Result<List<RickAndMortyModel>>>.combineWithSavedCharacters(): Flow<Result<List<RickAndMortyModel>>> {
        return combine(getSavedCharacterUseCase()) { result, savedCharacters ->
            when (result) {
                is Result.Success -> {
                    val updatedCharacters = result.data?.map { apiCharacter ->
                        apiCharacter.copy(isSaved = savedCharacters.any { savedCharacter -> savedCharacter.id == apiCharacter.id })
                    }
                    Result.Success(updatedCharacters.orEmpty())
                }
                is Result.Error, is Result.Loading -> result
            }
        }
    }

    private suspend fun Flow<Result<List<RickAndMortyModel>>>.collectResult() {
        collect { result ->
            when (result) {
                is Result.Success -> {
                    _characters.value = result.data ?: emptyList()
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _characters.value = emptyList()
                    _errorMessage.value = UiText.DynamicString(result.message)
                    _isLoading.value = false
                }
                is Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }
}