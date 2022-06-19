package com.example.mvpexample.data.mapper

import com.example.mvpexample.core.mapper.Mapper
import com.example.mvpexample.data.model.ComicsData
import com.example.mvpexample.domain.model.ComicsItem
import javax.inject.Inject

class ComicsMapper @Inject constructor() :
    Mapper<ComicsData, ComicsItem> {
    override fun map(type: ComicsData) = ComicsItem(
        type.comicsId,
        type.name,
        type.description,
        type.thumbnail
    )
}