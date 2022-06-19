package com.example.mvpexample.domain.base

import com.example.mvpexample.core.Result
import com.example.mvpexample.core.Result.Error
import com.example.mvpexample.core.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameter: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                Success(execute(parameter))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameter: P): R
}