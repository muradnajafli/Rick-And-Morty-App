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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSavedCharacterUseCase: GetSavedCharacterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchParams = MutableStateFlow(SearchParams())
    val searchParameters = _searchParams.asStateFlow()

    init {
        fetchCharacters()
    }

    fun onSearch(searchParams: SearchParams) {
        _searchParams.value = searchParams
        fetchCharacters()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun fetchCharacters() {
        viewModelScope.launch {
            searchParameters
                .debounce(500L)
                .flatMapLatest { searchParams ->
                    getCharactersUseCase(
                        name = searchParams.queryText,
                        status = searchParams.filterParams.currentStatus,
                        species = searchParams.filterParams.currentSpecies,
                        gender = searchParams.filterParams.currentGender
                    )
                }
                .combineWithSavedCharacters()
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = UiText.DynamicString(error.message ?: "Unknown error occurred")
                        )
                    }
                }
                .collect { result -> _uiState.update {
                    when (result) {
                        is Result.Success -> it.copy(
                            characters = result.data.orEmpty(),
                            isLoading = false,
                            error = null
                        )

                        is Result.Error -> it.copy(
                            characters = emptyList(),
                            isLoading = false,
                            error = UiText.DynamicString(result.message)
                        )

                        is Result.Loading -> it.copy(isLoading = result.isLoading)
                    }
                }
                }
        }
    }

    private suspend fun Flow<Result<List<RickAndMortyModel>>>.combineWithSavedCharacters(): Flow<Result<List<RickAndMortyModel>>> =
        combine(getSavedCharacterUseCase()) { result, savedCharacters ->
            when (result) {
                is Result.Success -> {
                    val updatedCharacters = result.data?.map { apiCharacter ->
                        apiCharacter.copy(isSaved = savedCharacters.any { it.id == apiCharacter.id })
                    }
                    Result.Success(updatedCharacters.orEmpty())
                }
                is Result.Error -> result
                is Result.Loading -> result
            }
        }
}