package com.example.mvpexample.cache.database.entity

import androidx.room.Entity

@Entity(tableName = "character_comics", primaryKeys = ["characterId", "comicsId"])
data class CharacterComicsCrossRef(
    val characterId: Long,
    val comicsId: Long
)