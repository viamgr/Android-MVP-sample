package com.example.mvpexample.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvpexample.cache.database.dao.CharacterComicsDao
import com.example.mvpexample.cache.database.dao.CharactersDao
import com.example.mvpexample.cache.database.dao.ComicsDao
import com.example.mvpexample.cache.database.entity.CharacterComics
import com.example.mvpexample.cache.database.entity.CharacterComicsCrossRef
import com.example.mvpexample.cache.database.entity.CharacterEntity
import com.example.mvpexample.cache.database.entity.ComicsEntity

@TypeConverters
@Database(
    entities = [
        CharacterEntity::class,
        ComicsEntity::class,
        CharacterComicsCrossRef::class,
    ],
    views = [CharacterComics::class],
    version = 1,
    exportSchema = false
)
abstract class MvpExampleRoomDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun comicsDao(): ComicsDao
    abstract fun characterComics(): CharacterComicsDao
}