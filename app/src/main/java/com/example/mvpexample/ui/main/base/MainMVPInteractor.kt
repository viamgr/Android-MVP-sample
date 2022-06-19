package com.example.mvpexample.ui.main.base

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.Result
import com.example.mvpexample.core.mvp.interactor.MVPInteractor
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.model.ComicsItem

interface MainMVPInteractor : MVPInteractor {
    suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Result<PagingResponse<CharacterItem>>

    suspend fun getComics(
        characterId: Long,
        offset: Int,
        limit: Int
    ): Result<PagingResponse<ComicsItem>>
}