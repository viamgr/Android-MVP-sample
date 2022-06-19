package com.example.mvpexample.remote.di

import com.example.mvpexample.data.datasource.character.CharacterRemote
import com.example.mvpexample.data.datasource.comics.ComicsRemote
import com.example.mvpexample.remote.datasource.CharacterRemoteDataSourceImpl
import com.example.mvpexample.remote.datasource.ComicsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun bindsCharactersRemoteDataSource(impl: CharacterRemoteDataSourceImpl): CharacterRemote

    @Binds
    @Singleton
    abstract fun bindsComicsRemoteDataSource(impl: ComicsRemoteDataSourceImpl): ComicsRemote
}