package com.muradnajafli.rickandmortyapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.rickandmortyapp.data.mapper.toRickAndMortyEntity
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.domain.usecase.details.AddCharacterUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.details.DeleteCharacterUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.details.GetCharacterByIdFromSaved
import com.muradnajafli.rickandmortyapp.domain.usecase.details.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.muradnajafli.rickandmortyapp.utils.Result
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val addCharacterUseCase: AddCharacterUseCase,
    private val removeCharacterUseCase: DeleteCharacterUseCase,
    private val getCharacterByIdFromSavedUseCase: GetCharacterByIdFromSaved,
): ViewModel() {

    private val _character = MutableStateFlow<RickAndMortyModel?>(null)
    val character = _character.asStateFlow()

    private val _isSaved = MutableStateFlow(false)
    val isSaved = _isSaved.asStateFlow()

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterByIdUseCase(id).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _character.value = result.data
                    }
                    is Result.Error -> {

                    }
                    is Result.Loading -> {
                        //
                    }
                }
            }
        }
    }

    fun checkIsSaved(id: Int) {
        viewModelScope.launch {
            _isSaved.value = getCharacterByIdFromSavedUseCase(id) != null
        }
    }

    fun addOrRemoveNewsFromSaved(character: RickAndMortyModel, isChecked: Boolean) {
        if (isChecked) {
            addCharacterToSaved(character)
        } else {
            deleteCharacterFromSaved(character)
        }
    }

    private fun addCharacterToSaved(character: RickAndMortyModel) {
        viewModelScope.launch {
            addCharacterUseCase(character.toRickAndMortyEntity())
            _isSaved.value = true
        }
    }

    private fun deleteCharacterFromSaved(character: RickAndMortyModel) {
        viewModelScope.launch {
            removeCharacterUseCase(character.toRickAndMortyEntity())
            _isSaved.value = false
        }
    }

}