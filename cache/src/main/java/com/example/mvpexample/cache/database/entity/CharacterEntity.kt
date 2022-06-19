package com.example.mvpexample.cache.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "character",
)
data class CharacterEntity(
    @PrimaryKey
    val characterId: Long,
    val description: String,
    val name: String,
    val thumbnail: String
)