package com.example.mvpexample.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val name: String,
    val resourceURI: String
)