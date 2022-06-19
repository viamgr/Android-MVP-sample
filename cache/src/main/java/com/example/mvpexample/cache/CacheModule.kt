package com.example.mvpexample.cache

import com.example.mvpexample.cache.datasource.character.CharacterLocalDataSourceImpl
import com.example.mvpexample.cache.datasource.comics.CharacterComicsLocalDataSourceImpl
import com.example.mvpexample.cache.datasource.comics.ComicsLocalDataSourceImpl
import com.example.mvpexample.data.datasource.character.CharacterLocal
import com.example.mvpexample.data.datasource.comics.CharacterComicsLocal
import com.example.mvpexample.data.datasource.comics.ComicsLocal
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
    @Binds
    @Singleton
    abstract fun bindsCharactersLocalDataSource(impl: CharacterLocalDataSourceImpl): CharacterLocal

    @Binds
    @Singleton
    abstract fun bindsComicsLocalDataSource(impl: ComicsLocalDataSourceImpl): ComicsLocal

    @Binds
    @Singleton
    abstract fun bindCharacterComicsLocalDataSource(impl: CharacterComicsLocalDataSourceImpl): CharacterComicsLocal
}