package com.example.mvpexample.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ComicsResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val thumbnail: ThumbnailResponse,
)