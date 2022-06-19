package com.example.mvpexample.cache.datasource.mapper

import com.example.mvpexample.cache.database.entity.CharacterEntity
import com.example.mvpexample.core.mapper.BiMapper
import com.example.mvpexample.data.model.CharacterData
import javax.inject.Inject

class LocalCharacterMapper @Inject constructor() : BiMapper<CharacterEntity, CharacterData> {
    override fun mapFromEntity(type: CharacterEntity): CharacterData {
        return CharacterData(
            characterId = type.characterId,
            description = type.description,
            name = type.name,
            thumbnail = type.thumbnail
        )
    }

    override fun mapToEntity(type: CharacterData): CharacterEntity {
        return CharacterEntity(
            characterId = type.characterId,
            description = type.description,
            name = type.name,
            thumbnail = type.thumbnail
        )
    }
}