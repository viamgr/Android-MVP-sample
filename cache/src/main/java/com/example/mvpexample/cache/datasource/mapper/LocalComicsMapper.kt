package com.example.mvpexample.cache.datasource.mapper

import com.example.mvpexample.cache.database.entity.ComicsEntity
import com.example.mvpexample.core.mapper.BiMapper
import com.example.mvpexample.data.model.ComicsData
import javax.inject.Inject

class LocalComicsMapper @Inject constructor() : BiMapper<ComicsEntity, ComicsData> {
    override fun mapFromEntity(type: ComicsEntity): ComicsData {
        return ComicsData(
            type.comicsId,
            description = type.description,
            name = type.name,
            thumbnail = type.thumbnail
        )
    }

    override fun mapToEntity(type: ComicsData): ComicsEntity {
        return ComicsEntity(
            type.comicsId,
            name = type.name,
            description = type.description,
            thumbnail = type.thumbnail
        )
    }
}