package com.example.mvpexample.core

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Exception) : Result<Nothing>()
}

inline fun <T> Result<T>.withResult(
    onSuccess: (T) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    when (this) {
        is Result.Error -> {
            onFailure(error)
        }
        is Result.Success -> {
            onSuccess(data)
        }
    }
}