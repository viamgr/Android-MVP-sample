package com.example.mvpexample

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.Result
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.domain.use_case.GetCharactersUseCase
import com.example.mvpexample.domain.use_case.GetComicsUseCase
import com.example.mvpexample.ui.main.impl.MainInteractor
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainInteractorTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @RelaxedMockK
    lateinit var getComicsUseCase: GetComicsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

    }

    private fun createInteractor(): MainInteractor {
        return MainInteractor(getCharactersUseCase, getComicsUseCase)
    }


    @Test
    fun getCharacters_requestParamsSetProperly() = runTest {
        val interactor = createInteractor()
        val offset = 213213
        val limit = 123456
        val mockk = mockk<Result<PagingResponse<CharacterItem>>>()
        coEvery { getCharactersUseCase(GetCharactersUseCase.Request(offset, limit)) } returns mockk
        assertEquals(mockk, interactor.getCharacters(offset, limit))
    }


    @Test
    fun getComics_requestParamsSetProperly() = runTest {
        val interactor = createInteractor()
        val characterId = 65498L
        val offset = 213213
        val limit = 123456
        val mockk = mockk<Result<PagingResponse<ComicsItem>>>()
        coEvery {
            getComicsUseCase(
                GetComicsUseCase.Request(
                    characterId,
                    offset,
                    limit
                )
            )
        } returns mockk
        assertEquals(mockk, interactor.getComics(characterId, offset, limit))
    }

}