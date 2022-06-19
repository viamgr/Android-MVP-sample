package com.example.mvpexample.cache.mapper

import com.example.mvpexample.cache.database.entity.CharacterEntity
import com.example.mvpexample.cache.datasource.mapper.LocalCharacterMapper
import com.example.mvpexample.data.model.CharacterData
import io.mockk.MockKAnnotations
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalCharacterMapperTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createLocalCharacterMapper(): LocalCharacterMapper {
        return LocalCharacterMapper()
    }

    @Test
    fun mapFromEntity_mapsCorrectly() {
        val localCharacterMapper = createLocalCharacterMapper()
        val name = "name"
        val thumbnail = "description"
        val description = "description"
        val characterId = 13432523321L
        val result = localCharacterMapper.mapFromEntity(
            CharacterEntity(
                characterId = characterId,
                name = name,
                description = description,
                thumbnail = thumbnail
            )
        )
        assertEquals(result.characterId, characterId)
        assertEquals(result.name, name)
        assertEquals(result.description, description)
        assertEquals(result.thumbnail, thumbnail)
    }

    @Test
    fun mapToEntity_mapsCorrectly() {
        val localCharacterMapper = createLocalCharacterMapper()
        val name = "name"
        val description = "description"
        val characterId = 13432523321L
        val thumbnail = "description"

        val result = localCharacterMapper.mapToEntity(
            CharacterData(
                characterId = characterId,
                name = name,
                description = description,
                thumbnail = thumbnail
            )
        )
        assertEquals(result.characterId, characterId)
        assertEquals(result.name, name)
        assertEquals(result.description, description)
        assertEquals(result.thumbnail, thumbnail)
    }

}