package com.example.mvpexample.cache

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MvpExampleRoomDatabase {
        return Room.databaseBuilder(context, MvpExampleRoomDatabase::class.java, "my-db")
            .enableMultiInstanceInvalidation()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(db: MvpExampleRoomDatabase) = db.charactersDao()

    @Provides
    @Singleton
    fun provideComicsDao(db: MvpExampleRoomDatabase) = db.comicsDao()

    @Provides
    @Singleton
    fun provideCharacterComicsDao(db: MvpExampleRoomDatabase) = db.characterComics()

}