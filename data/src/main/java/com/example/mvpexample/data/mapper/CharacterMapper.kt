package com.example.mvpexample.data.mapper

import com.example.mvpexample.core.mapper.Mapper
import com.example.mvpexample.data.model.CharacterData
import com.example.mvpexample.domain.model.CharacterItem
import javax.inject.Inject

class CharacterMapper @Inject constructor() :
    Mapper<CharacterData, CharacterItem> {
    override fun map(type: CharacterData) = CharacterItem(
        type.characterId,
        type.name,
        type.thumbnail
    )
}