package com.example.mvpexample.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<T>,
    val total: Int
)