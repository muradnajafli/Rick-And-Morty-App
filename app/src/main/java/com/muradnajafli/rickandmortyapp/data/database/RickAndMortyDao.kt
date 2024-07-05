package com.muradnajafli.rickandmortyapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muradnajafli.rickandmortyapp.data.entity.RickAndMortyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RickAndMortyDao {

    @Query("SELECT * FROM rick_and_morty")
    fun getSavedCharacters(): Flow<List<RickAndMortyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rickAndMortyEntity: RickAndMortyEntity)

    @Delete
    suspend fun delete(rickAndMortyEntity: RickAndMortyEntity)

    @Query("SELECT * FROM rick_and_morty WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): RickAndMortyEntity?

}