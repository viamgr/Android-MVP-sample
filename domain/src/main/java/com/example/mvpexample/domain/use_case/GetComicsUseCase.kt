package com.example.mvpexample.domain.use_case

import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.di.IoDispatcher
import com.example.mvpexample.domain.base.UseCase
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.domain.repository.ComicsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(
    private val comicsRepository: ComicsRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : UseCase<GetComicsUseCase.Request, PagingResponse<ComicsItem>>(coroutineDispatcher = coroutineDispatcher) {

    data class Request(
        val characterId: Long,
        val offset: Int,
        val limit: Int
    )

    override suspend fun execute(parameter: Request): PagingResponse<ComicsItem> {
        return comicsRepository.getComics(
            characterId = parameter.characterId,
            offset = parameter.offset,
            limit = parameter.limit
        )
    }
}