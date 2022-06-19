package com.example.mvpexample.data.repository

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.map
import com.example.mvpexample.data.datasource.character.CharacterLocal
import com.example.mvpexample.data.datasource.character.CharacterRemote
import com.example.mvpexample.data.mapper.CharacterMapper
import com.example.mvpexample.data.model.CharacterData
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterRemote: CharacterRemote,
    private val characterLocal: CharacterLocal,
    private val characterMapper: CharacterMapper,

    ) : CharactersRepository {
    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): PagingResponse<CharacterItem> {
        val localPagingData = characterLocal
            .getCharacters(offset = offset, limit = limit)
            .map { characterMapper.map(it) }

        return if (localPagingData.data.isEmpty()) {
            val remotePagingData = characterRemote.getCharacters(limit = limit, offset = offset)
            val mappedCachedData = cacheData(remotePagingData.data)

            PagingResponse(
                remotePagingData.isReachedEnd,
                mappedCachedData
            )
        } else {
            localPagingData
        }
    }

    private suspend fun cacheData(data: List<CharacterData>): List<CharacterItem> {
        return data.map { characterData ->
            characterLocal.insert(characterData)
            characterMapper.map(characterData)
        }
    }

}