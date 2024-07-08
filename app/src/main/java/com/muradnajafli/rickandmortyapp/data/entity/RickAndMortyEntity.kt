package com.muradnajafli.rickandmortyapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "rick_and_morty")
data class RickAndMortyEntity(
    @PrimaryKey val id: Int,
)
