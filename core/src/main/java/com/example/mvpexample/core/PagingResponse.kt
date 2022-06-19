package com.example.mvpexample.core

data class PagingResponse<T>(
    val isReachedEnd: Boolean,
    val data: List<T>
)

fun <T, R> PagingResponse<T>.map(mapper: (T) -> R): PagingResponse<R> {
    return PagingResponse(isReachedEnd, data.map(mapper))
}