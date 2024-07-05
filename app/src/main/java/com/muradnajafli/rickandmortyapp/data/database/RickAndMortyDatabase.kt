package com.muradnajafli.rickandmortyapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muradnajafli.rickandmortyapp.data.entity.RickAndMortyEntity


@Database(entities = [RickAndMortyEntity::class], version = 1, exportSchema = false)
abstract class RickAndMortyDatabase: RoomDatabase() {
    abstract fun rickAndMortyDao(): RickAndMortyDao
}