package com.muradnajafli.rickandmortyapp.di

import com.muradnajafli.rickandmortyapp.domain.repository.local.SavedRickAndMortyRepository
import com.muradnajafli.rickandmortyapp.domain.repository.remote.RickAndMortyRepository
import com.muradnajafli.rickandmortyapp.domain.usecase.details.AddCharacterUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.details.AddCharacterUseCaseImpl
import com.muradnajafli.rickandmortyapp.domain.usecase.details.DeleteCharacterUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.details.DeleteCharacterUseCaseImpl
import com.muradnajafli.rickandmortyapp.domain.usecase.details.GetCharacterByIdFromSaved
import com.muradnajafli.rickandmortyapp.domain.usecase.details.GetCharacterByIdFromSavedImpl
import com.muradnajafli.rickandmortyapp.domain.usecase.details.GetCharacterByIdUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.details.GetCharacterByIdUseCaseImpl
import com.muradnajafli.rickandmortyapp.domain.usecase.home.GetCharactersUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.home.GetCharactersUseCaseImpl
import com.muradnajafli.rickandmortyapp.domain.usecase.home.GetSavedCharacterUseCase
import com.muradnajafli.rickandmortyapp.domain.usecase.home.GetSavedCharacterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.scopes.ViewScoped
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetCharactersUseCase(
        rickAndMortyRepository: RickAndMortyRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(rickAndMortyRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetSavedCharactersUseCase(
        savedRickAndMortyRepository: SavedRickAndMortyRepository
    ): GetSavedCharacterUseCase {
        return GetSavedCharacterUseCaseImpl(savedRickAndMortyRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCharacterByIdUseCase(
        rickAndMortyRepository: RickAndMortyRepository
    ): GetCharacterByIdUseCase {
        return GetCharacterByIdUseCaseImpl(rickAndMortyRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteCharacterUseCase(
        savedRickAndMortyRepository: SavedRickAndMortyRepository
    ): DeleteCharacterUseCase {
        return DeleteCharacterUseCaseImpl(savedRickAndMortyRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideAddCharacterUseCase(
        savedRickAndMortyRepository: SavedRickAndMortyRepository
    ): AddCharacterUseCase {
        return AddCharacterUseCaseImpl(savedRickAndMortyRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCharacterByIdFromSavedUseCase(
        savedRickAndMortyRepository: SavedRickAndMortyRepository
    ): GetCharacterByIdFromSaved {
        return GetCharacterByIdFromSavedImpl(savedRickAndMortyRepository)
    }



}