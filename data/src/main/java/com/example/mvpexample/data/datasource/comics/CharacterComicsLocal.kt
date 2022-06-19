package com.example.mvpexample.data.datasource.comics

import com.example.mvpexample.data.model.CharacterComicsData

interface CharacterComicsLocal {

    suspend fun insert(characterComicsData: CharacterComicsData)
}