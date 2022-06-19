package com.example.mvpexample.remote.mapper

import com.example.mvpexample.core.mapper.Mapper
import com.example.mvpexample.data.model.ComicsData
import com.example.mvpexample.remote.model.ComicsResponse
import javax.inject.Inject

class ComicsRemoteMapper @Inject constructor() : Mapper<ComicsResponse, ComicsData> {

    override fun map(type: ComicsResponse) = ComicsData(
        comicsId = type.id,
        description = type.description,
        name = type.title,
        thumbnail = type.thumbnail.path + "." + type.thumbnail.extension
    )
}