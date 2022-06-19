package com.example.mvpexample.cache.datasource.comics

import com.example.mvpexample.cache.database.dao.CharacterComicsDao
import com.example.mvpexample.cache.datasource.mapper.LocalCharacterComicsMapper
import com.example.mvpexample.data.datasource.comics.CharacterComicsLocal
import com.example.mvpexample.data.model.CharacterComicsData
import javax.inject.Inject

class CharacterComicsLocalDataSourceImpl @Inject constructor(
    private val localCharacterComicsMapper: LocalCharacterComicsMapper,
    private val characterComicsDao: CharacterComicsDao
) : CharacterComicsLocal {
    override suspend fun insert(characterComicsData: CharacterComicsData) {
        characterComicsDao.insert(localCharacterComicsMapper.mapToEntity(characterComicsData))
    }

}