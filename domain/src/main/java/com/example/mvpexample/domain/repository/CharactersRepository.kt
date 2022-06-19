package com.example.mvpexample.domain.repository

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.domain.model.CharacterItem

interface CharactersRepository {
    suspend fun getCharacters(offset: Int, limit: Int): PagingResponse<CharacterItem>
}