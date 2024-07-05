package com.muradnajafli.rickandmortyapp.di

import com.muradnajafli.rickandmortyapp.data.repository.local.SavedRickAndMortyRepositoryImpl
import com.muradnajafli.rickandmortyapp.data.repository.remote.RickAndMortyRepositoryImpl
import com.muradnajafli.rickandmortyapp.domain.repository.local.SavedRickAndMortyRepository
import com.muradnajafli.rickandmortyapp.domain.repository.remote.RickAndMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRickAndMortyRepository(
        rickAndMortyRepositoryImpl: RickAndMortyRepositoryImpl
    ): RickAndMortyRepository

    @Binds
    @Singleton
    fun bindSavedRickAndMortyRepository(
        savedRickAndMortyRepositoryImpl: SavedRickAndMortyRepositoryImpl
    ): SavedRickAndMortyRepository
}