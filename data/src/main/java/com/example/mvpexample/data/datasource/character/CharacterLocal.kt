package com.example.mvpexample.data.datasource.character

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.model.CharacterData

interface CharacterLocal {
    suspend fun getCharacters(
        limit: Int,
        offset: Int
    ): PagingResponse<CharacterData>

    suspend fun insert(characterData: CharacterData)
}