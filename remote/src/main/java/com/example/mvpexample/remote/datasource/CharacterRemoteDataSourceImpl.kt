package com.example.mvpexample.remote.datasource

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.datasource.character.CharacterRemote
import com.example.mvpexample.data.model.CharacterData
import com.example.mvpexample.remote.MarvelApi
import com.example.mvpexample.remote.mapper.CharacterRemoteMapper
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterRemoteMapper: CharacterRemoteMapper,
    private val marvelApi: MarvelApi,
) : CharacterRemote {
    override suspend fun getCharacters(limit: Int, offset: Int): PagingResponse<CharacterData> {
        val data = marvelApi.getCharacters(limit = limit, offset = offset).data
        val isReachedEnd = data.count + offset >= data.total
        return PagingResponse(
            isReachedEnd = isReachedEnd,
            data = data.results.map { characterRemoteMapper.map(it) }
        )
    }
}