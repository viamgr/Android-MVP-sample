package com.example.mvpexample.cache.mapper

import com.example.mvpexample.cache.database.entity.CharacterComicsCrossRef
import com.example.mvpexample.cache.datasource.mapper.LocalCharacterComicsMapper
import com.example.mvpexample.data.model.CharacterComicsData
import io.mockk.MockKAnnotations
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalCharacterComicsMapperTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createLocalCharacterComicsMapper(): LocalCharacterComicsMapper {
        return LocalCharacterComicsMapper()
    }

    @Test
    fun mapFromEntity_mapsCorrectly() {
        val localCharacterComicsMapper = createLocalCharacterComicsMapper()
        val characterId = 1523321L
        val comicsId = 13432523321L
        val result = localCharacterComicsMapper.mapFromEntity(
            CharacterComicsCrossRef(
                characterId = characterId,
                comicsId = comicsId
            )
        )
        assertEquals(result.comicsId, comicsId)
        assertEquals(result.characterId, characterId)
    }

    @Test
    fun mapToEntity_mapsCorrectly() {
        val localCharacterComicsMapper = createLocalCharacterComicsMapper()
        val characterId = 1523321L
        val comicsId = 13432523321L
        val result = localCharacterComicsMapper.mapToEntity(
            CharacterComicsData(
                characterId = characterId,
                comicsId = comicsId
            )
        )
        assertEquals(result.comicsId, comicsId)
        assertEquals(result.characterId, characterId)
    }

}