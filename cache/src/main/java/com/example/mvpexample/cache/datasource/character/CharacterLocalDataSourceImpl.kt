package com.example.mvpexample.cache.datasource.character

import com.example.mvpexample.cache.database.dao.CharactersDao
import com.example.mvpexample.cache.datasource.mapper.LocalCharacterMapper
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.datasource.character.CharacterLocal
import com.example.mvpexample.data.model.CharacterData
import javax.inject.Inject

class CharacterLocalDataSourceImpl @Inject constructor(
    private val localCharacterMapper: LocalCharacterMapper,
    private val charactersDao: CharactersDao,
) : CharacterLocal {
    override suspend fun getCharacters(limit: Int, offset: Int): PagingResponse<CharacterData> {
        val data = charactersDao.getList(offset = offset, limit = limit)
        val isReachedEnd = data.size < limit
        return PagingResponse(
            isReachedEnd = isReachedEnd,
            data = data.map { localCharacterMapper.mapFromEntity(it) }
        )
    }

    override suspend fun insert(characterData: CharacterData) {
        charactersDao.insert(localCharacterMapper.mapToEntity(characterData))
    }

}