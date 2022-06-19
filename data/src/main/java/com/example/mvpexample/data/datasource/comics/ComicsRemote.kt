package com.example.mvpexample.data.datasource.comics

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.model.ComicsData

interface ComicsRemote {
    suspend fun getComics(
        characterId: Long,
        limit: Int,
        offset: Int
    ): PagingResponse<ComicsData>
}