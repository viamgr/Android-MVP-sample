package com.example.mvpexample.remote

import com.example.mvpexample.remote.model.BaseResponse
import com.example.mvpexample.remote.model.CharacterResponse
import com.example.mvpexample.remote.model.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters?orderBy=name")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): BaseResponse<CharacterResponse>

    @GET("/v1/public/characters/{characterId}/comics?orderBy=title")
    suspend fun getComics(
        @Path("characterId") characterId: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): BaseResponse<ComicsResponse>
}