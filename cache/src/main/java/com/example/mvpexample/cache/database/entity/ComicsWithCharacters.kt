package com.example.mvpexample.cache.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ComicsWithCharacters(
    @Embedded val comics: ComicsEntity,
    @Relation(
        parentColumn = "comicsId",
        entityColumn = "characterId",
        associateBy = Junction(CharacterComicsCrossRef::class)
    )
    val characters: List<CharacterEntity>
)
