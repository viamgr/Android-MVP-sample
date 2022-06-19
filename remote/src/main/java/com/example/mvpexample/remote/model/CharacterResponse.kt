package com.example.mvpexample.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val description: String,
    val id: Long,
    val name: String,
    val thumbnail: ThumbnailResponse,
)