package com.example.mvpexample

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.Result
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.domain.repository.ComicsRepository
import com.example.mvpexample.domain.use_case.GetComicsUseCase
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


class GetComicsUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var comicsRepository: ComicsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createGetComicsUseCase(): GetComicsUseCase {
        return GetComicsUseCase(comicsRepository, UnconfinedTestDispatcher())
    }

    @Test
    fun getComics_argumentParamsSetProperly() = runTest {
        val getComicsUseCase = createGetComicsUseCase()
        val offset = 10
        val limit = 1
        val comicsId = 1546545L
        getComicsUseCase(GetComicsUseCase.Request(comicsId, offset, limit))
        coVerify { comicsRepository.getComics(comicsId, offset, limit) }
    }

    @Test
    fun getComics_returnError_onThrowError() = runTest {
        val exception = Exception("")
        coEvery { comicsRepository.getComics(any(), any(), any()) } throws exception
        val getComicsUseCase = createGetComicsUseCase()
        val result = getComicsUseCase(mockk(relaxed = true))
        Assert.assertTrue(result is Result.Error)
        Assert.assertEquals(exception, (result as Result.Error).error)
    }

    @Test
    fun getComics_returnSuccess_onSuccessResult() = runTest {
        val result: PagingResponse<ComicsItem> = mockk()
        coEvery { comicsRepository.getComics(any(), any(), any()) } returns result
        val getComicsUseCaseResult = createGetComicsUseCase().invoke(mockk(relaxed = true))
        Assert.assertTrue(getComicsUseCaseResult is Result.Success)
        Assert.assertEquals(result, (getComicsUseCaseResult as Result.Success).data)
    }


}