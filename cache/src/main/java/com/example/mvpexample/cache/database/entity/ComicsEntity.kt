package com.example.mvpexample.cache.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "comics",
    indices = [Index(value = ["name"], unique = true)]
)
data class ComicsEntity(
    @PrimaryKey var comicsId: Long,
    val name: String,
    val description: String?,
    val thumbnail: String
)