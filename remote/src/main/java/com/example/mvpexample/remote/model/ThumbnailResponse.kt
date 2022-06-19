package com.example.mvpexample.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailResponse(
    val extension: String,
    val path: String
)