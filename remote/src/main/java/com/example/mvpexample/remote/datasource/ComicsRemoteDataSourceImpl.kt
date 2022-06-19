package com.example.mvpexample.remote.datasource

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.datasource.comics.ComicsRemote
import com.example.mvpexample.data.model.ComicsData
import com.example.mvpexample.remote.MarvelApi
import com.example.mvpexample.remote.mapper.ComicsRemoteMapper
import javax.inject.Inject

class ComicsRemoteDataSourceImpl @Inject constructor(
    private val marvelApi: MarvelApi,
    private val comicsRemoteMapper: ComicsRemoteMapper,
) : ComicsRemote {
    override suspend fun getComics(
        characterId: Long,
        limit: Int,
        offset: Int
    ): PagingResponse<ComicsData> {
        val data =
            marvelApi.getComics(characterId = characterId, limit = limit, offset = offset).data
        val isReachedEnd = data.count + offset >= data.total
        return PagingResponse(
            isReachedEnd = isReachedEnd,
            data = data.results.map { comicsRemoteMapper.map(it) }
        )
    }
}