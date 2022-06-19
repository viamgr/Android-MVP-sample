package com.example.mvpexample.cache.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.mvpexample.cache.database.entity.CharacterEntity

@Dao
interface CharactersDao : BaseDao<CharacterEntity> {

    @Transaction
    @Query("SELECT * FROM character ORDER BY name ASC LIMIT :limit OFFSET :offset")
    suspend fun getList(limit: Int, offset: Int): List<CharacterEntity>

    @Query("SELECT count(characterId) FROM character")
    suspend fun getItemCounts(): Int

}