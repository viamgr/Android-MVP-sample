package com.example.mvpexample.datasource.character

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.cache.database.dao.CharactersDao
import com.example.mvpexample.cache.datasource.character.CharacterLocalDataSourceImpl
import com.example.mvpexample.cache.datasource.mapper.LocalCharacterMapper
import com.example.mvpexample.data.model.CharacterData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CharacterLocalDataSourceImplTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var localCharacterMapper: LocalCharacterMapper

    @RelaxedMockK
    lateinit var charactersDao: CharactersDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createCharacterLocalDataSource(): CharacterLocalDataSourceImpl {
        return CharacterLocalDataSourceImpl(localCharacterMapper, charactersDao)
    }

    @Test
    fun getCharacters_getListWorksProperly() = runTest {
        val characterLocalDataSource = createCharacterLocalDataSource()
        val limit = 10
        val offset = 446546
        coEvery { charactersDao.getList(limit, offset) } returns emptyList()
        characterLocalDataSource.getCharacters(limit = limit, offset = offset)
        coEvery { charactersDao.getList(limit, offset) }
    }

    @Test
    fun insert_insertsProperly() = runTest {
        val characterLocalDataSource = createCharacterLocalDataSource()
        val characterData = mockk<CharacterData>()
        characterLocalDataSource.insert(characterData = characterData)
        coEvery { localCharacterMapper.mapToEntity(characterData) }
    }
}