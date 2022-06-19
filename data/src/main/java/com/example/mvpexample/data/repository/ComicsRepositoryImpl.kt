package com.example.mvpexample.data.repository

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.map
import com.example.mvpexample.data.datasource.comics.CharacterComicsLocal
import com.example.mvpexample.data.datasource.comics.ComicsLocal
import com.example.mvpexample.data.datasource.comics.ComicsRemote
import com.example.mvpexample.data.mapper.ComicsMapper
import com.example.mvpexample.data.model.CharacterComicsData
import com.example.mvpexample.data.model.ComicsData
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.domain.repository.ComicsRepository
import javax.inject.Inject

class ComicsRepositoryImpl @Inject constructor(
    private val comicsMapper: ComicsMapper,
    private val comicsRemote: ComicsRemote,
    private val comicsLocal: ComicsLocal,
    private val characterComicsLocal: CharacterComicsLocal,
) : ComicsRepository {
    override suspend fun getComics(
        characterId: Long,
        offset: Int,
        limit: Int
    ): PagingResponse<ComicsItem> {
        val localPagingData = comicsLocal
            .getComics(characterId = characterId, offset = offset, limit = limit)
            .map { comicsMapper.map(it) }

        return if (localPagingData.data.isEmpty()) {
            val remotePagingData =
                comicsRemote.getComics(characterId = characterId, limit = limit, offset = offset)
            val mappedCachedData = cacheComics(characterId, remotePagingData.data)

            PagingResponse(
                remotePagingData.isReachedEnd,
                mappedCachedData
            )
        } else {
            localPagingData
        }
    }

    private suspend fun cacheComics(
        characterId: Long,
        data: List<ComicsData>
    ): List<ComicsItem> {
        return data.map { comicsData ->
            comicsLocal.insert(comicsData)
            characterComicsLocal.insert(CharacterComicsData(characterId, comicsData.comicsId))
            comicsMapper.map(comicsData)
        }
    }

}