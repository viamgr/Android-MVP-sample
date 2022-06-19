package com.example.mvpexample.di

import com.example.mvpexample.data.repository.CharactersRepositoryImpl
import com.example.mvpexample.data.repository.ComicsRepositoryImpl
import com.example.mvpexample.domain.repository.CharactersRepository
import com.example.mvpexample.domain.repository.ComicsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @Singleton
    abstract fun bindsComicsRepository(impl: ComicsRepositoryImpl): ComicsRepository
}