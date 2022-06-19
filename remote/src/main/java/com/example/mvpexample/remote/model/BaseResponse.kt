package com.example.mvpexample.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: DataResponse<T>,
    val status: String
)

