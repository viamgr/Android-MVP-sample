package com.example.mvpexample.cache.mapper

import com.example.mvpexample.cache.database.entity.ComicsEntity
import com.example.mvpexample.cache.datasource.mapper.LocalComicsMapper
import com.example.mvpexample.data.model.ComicsData
import io.mockk.MockKAnnotations
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalComicsMapperTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createLocalComicsMapper(): LocalComicsMapper {
        return LocalComicsMapper()
    }

    @Test
    fun mapFromEntity_mapsCorrectly() {
        val localComicsMapper = createLocalComicsMapper()
        val name = "name"
        val thumbnail = "description"
        val description = "description"
        val comicsId = 13432523321L
        val result = localComicsMapper.mapFromEntity(
            ComicsEntity(
                comicsId = comicsId,
                name = name,
                description = description,
                thumbnail = thumbnail
            )
        )
        assertEquals(result.comicsId, comicsId)
        assertEquals(result.name, name)
        assertEquals(result.description, description)
        assertEquals(result.thumbnail, thumbnail)
    }

    @Test
    fun mapToEntity_mapsCorrectly() {
        val localComicsMapper = createLocalComicsMapper()
        val name = "name"
        val description = "description"
        val comicsId = 13432523321L
        val thumbnail = "description"

        val result = localComicsMapper.mapToEntity(
            ComicsData(
                comicsId = comicsId,
                name = name,
                description = description,
                thumbnail = thumbnail
            )
        )
        assertEquals(result.comicsId, comicsId)
        assertEquals(result.name, name)
        assertEquals(result.description, description)
        assertEquals(result.thumbnail, thumbnail)
    }

}