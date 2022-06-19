package com.example.mvpexample.remote.mapper

import com.example.mvpexample.core.mapper.Mapper
import com.example.mvpexample.data.model.CharacterData
import com.example.mvpexample.remote.model.CharacterResponse
import javax.inject.Inject

class CharacterRemoteMapper @Inject constructor() : Mapper<CharacterResponse, CharacterData> {

    override fun map(type: CharacterResponse) = CharacterData(
        characterId = type.id,
        description = type.description,
        name = type.name,
        thumbnail = type.thumbnail.path + "." + type.thumbnail.extension
    )
}