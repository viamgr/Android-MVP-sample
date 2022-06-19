package com.example.mvpexample.domain.use_case

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.di.IoDispatcher
import com.example.mvpexample.domain.base.UseCase
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) :
    UseCase<GetCharactersUseCase.Request, PagingResponse<CharacterItem>>(coroutineDispatcher = coroutineDispatcher) {
    data class Request(
        val offset: Int,
        val limit: Int
    )

    override suspend fun execute(parameter: Request): PagingResponse<CharacterItem> {
        return charactersRepository.getCharacters(
            offset = parameter.offset,
            limit = parameter.limit
        )
    }
}