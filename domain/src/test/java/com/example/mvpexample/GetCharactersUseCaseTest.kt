package com.example.mvpexample

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.Result
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.repository.CharactersRepository
import com.example.mvpexample.domain.use_case.GetCharactersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetCharactersUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var charactersRepository: CharactersRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createGetCharactersUseCase(): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository, UnconfinedTestDispatcher())
    }

    @Test
    fun getCharacters_argumentParamsSetProperly() = runTest {
        val getCharactersUseCase = createGetCharactersUseCase()
        val offset = 10
        val limit = 1
        getCharactersUseCase(GetCharactersUseCase.Request(offset, limit))
        coVerify { charactersRepository.getCharacters(offset, limit) }
    }

    @Test
    fun getCharacters_returnError_onThrowError() = runTest {
        val exception = Exception("")
        coEvery { charactersRepository.getCharacters(any(), any()) } throws exception
        val getCharactersUseCase = createGetCharactersUseCase()
        val result = getCharactersUseCase(mockk(relaxed = true))
        Assert.assertTrue(result is Result.Error)
        Assert.assertEquals(exception, (result as Result.Error).error)
    }

    @Test
    fun getCharacters_returnSuccess_onSuccessResult() = runTest {
        val result: PagingResponse<CharacterItem> = mockk()
        coEvery { charactersRepository.getCharacters(any(), any()) } returns result
        val getCharactersUseCaseResult = createGetCharactersUseCase().invoke(mockk(relaxed = true))
        Assert.assertTrue(getCharactersUseCaseResult is Result.Success)
        Assert.assertEquals(result, (getCharactersUseCaseResult as Result.Success).data)
    }


}