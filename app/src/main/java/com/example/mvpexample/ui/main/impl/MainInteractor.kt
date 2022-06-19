package com.example.mvpexample.ui.main.impl

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.Result
import com.example.mvpexample.core.mvp.interactor.BaseInteractor
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.domain.use_case.GetCharactersUseCase
import com.example.mvpexample.domain.use_case.GetComicsUseCase
import com.example.mvpexample.ui.main.base.MainMVPInteractor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainInteractor @Inject internal constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getComicsUseCase: GetComicsUseCase,
) : BaseInteractor(), MainMVPInteractor {
    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Result<PagingResponse<CharacterItem>> =
        getCharactersUseCase(GetCharactersUseCase.Request(offset = offset, limit = limit))

    override suspend fun getComics(
        characterId: Long,
        offset: Int,
        limit: Int
    ): Result<PagingResponse<ComicsItem>> {
        return getComicsUseCase(
            GetComicsUseCase.Request(
                characterId = characterId,
                offset = offset,
                limit = limit
            )
        )
    }


}

