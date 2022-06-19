package com.example.mvpexample.cache.datasource.comics

import com.example.mvpexample.cache.database.dao.ComicsDao
import com.example.mvpexample.cache.database.entity.ComicsEntity
import com.example.mvpexample.cache.datasource.mapper.LocalComicsMapper
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.datasource.comics.ComicsLocal
import com.example.mvpexample.data.model.ComicsData
import javax.inject.Inject

class ComicsLocalDataSourceImpl @Inject constructor(
    private val localComicsMapper: LocalComicsMapper,
    private val comicsDao: ComicsDao,
) : ComicsLocal {
    override suspend fun getComics(
        characterId: Long,
        limit: Int,
        offset: Int
    ): PagingResponse<ComicsData> {
        val data = comicsDao.getComicsWithCharacters(
            characterId = characterId,
            offset = offset,
            limit = limit
        ).map { ComicsEntity(it.comicsId, it.name, it.description, it.thumbnail) }
        val isReachedEnd = data.size < limit
        return PagingResponse(
            isReachedEnd = isReachedEnd,
            data = data.map { localComicsMapper.mapFromEntity(it) }
        )
    }

    override suspend fun insert(comicsData: ComicsData) {
        comicsDao.insert(localComicsMapper.mapToEntity(comicsData))
    }

}