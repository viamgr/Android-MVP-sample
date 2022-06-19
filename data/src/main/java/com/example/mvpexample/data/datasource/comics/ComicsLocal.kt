package com.example.mvpexample.data.datasource.comics

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.model.ComicsData

interface ComicsLocal {
    suspend fun getComics(
        characterId: Long,
        limit: Int,
        offset: Int
    ): PagingResponse<ComicsData>

    suspend fun insert(comicsData: ComicsData)
}