package com.example.mvpexample.domain.repository

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.domain.model.ComicsItem

interface ComicsRepository {
    suspend fun getComics(characterId: Long, offset: Int, limit: Int): PagingResponse<ComicsItem>
}