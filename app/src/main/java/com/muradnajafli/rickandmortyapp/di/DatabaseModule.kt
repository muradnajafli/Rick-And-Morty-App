package com.muradnajafli.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muradnajafli.rickandmortyapp.data.database.RickAndMortyDao
import com.muradnajafli.rickandmortyapp.data.database.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RickAndMortyDatabase {
        return Room.databaseBuilder(
            context,
            RickAndMortyDatabase::class.java,
            "fav_movie_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyDao(db: RickAndMortyDatabase): RickAndMortyDao {
        return db.rickAndMortyDao()
    }

}