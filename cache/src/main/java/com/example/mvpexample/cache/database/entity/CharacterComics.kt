package com.example.mvpexample.cache.database.entity

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT character_comics.characterId as characterId,comics.comicsId as comicsId, " +
            "comics.name as name, comics.description as description, comics.thumbnail as thumbnail" +
            " FROM comics " +
            "INNER JOIN character_comics ON comics.comicsId = character_comics.comicsId " +
            "ORDER BY comics.name ASC",
    viewName = "view_character_comics"
)
data class CharacterComics(
    val characterId: Long,
    val comicsId: Long,
    val name: String,
    val description: String?,
    val thumbnail: String
)