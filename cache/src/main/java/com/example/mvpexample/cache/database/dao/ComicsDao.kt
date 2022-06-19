package com.example.mvpexample.cache.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.mvpexample.cache.database.entity.CharacterComics
import com.example.mvpexample.cache.database.entity.ComicsEntity

@Dao
interface ComicsDao : BaseDao<ComicsEntity> {

    @Transaction
    @Query("SELECT * FROM view_character_comics WHERE characterId=:characterId LIMIT :limit OFFSET :offset")
    suspend fun getComicsWithCharacters(
        characterId: Long,
        limit: Int,
        offset: Int
    ): List<CharacterComics>
}