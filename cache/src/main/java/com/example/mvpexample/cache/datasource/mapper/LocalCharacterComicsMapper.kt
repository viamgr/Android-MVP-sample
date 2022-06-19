package com.example.mvpexample.cache.datasource.mapper

import com.example.mvpexample.cache.database.entity.CharacterComicsCrossRef
import com.example.mvpexample.core.mapper.BiMapper
import com.example.mvpexample.data.model.CharacterComicsData
import javax.inject.Inject

class LocalCharacterComicsMapper @Inject constructor() :
    BiMapper<CharacterComicsCrossRef, CharacterComicsData> {
    override fun mapFromEntity(type: CharacterComicsCrossRef): CharacterComicsData {
        return CharacterComicsData(type.characterId, type.comicsId)
    }

    override fun mapToEntity(type: CharacterComicsData): CharacterComicsCrossRef {
        return CharacterComicsCrossRef(type.characterId, type.comicsId)
    }
}