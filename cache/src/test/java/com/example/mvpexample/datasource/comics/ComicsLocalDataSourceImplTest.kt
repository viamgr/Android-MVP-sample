package com.example.mvpexample.datasource.comics

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.cache.database.dao.ComicsDao
import com.example.mvpexample.cache.datasource.comics.ComicsLocalDataSourceImpl
import com.example.mvpexample.cache.datasource.mapper.LocalComicsMapper
import com.example.mvpexample.data.model.ComicsData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ComicsLocalDataSourceImplTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var localComicsMapper: LocalComicsMapper

    @RelaxedMockK
    lateinit var comicsDao: ComicsDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createComicsLocalDataSource(): ComicsLocalDataSourceImpl {
        return ComicsLocalDataSourceImpl(localComicsMapper, comicsDao)
    }

    @Test
    fun getComics_getListWorksProperly() = runTest {
        val comicsLocalDataSource = createComicsLocalDataSource()
        val limit = 10
        val characterId = 1026343L
        val offset = 446546
        coEvery {
            comicsDao.getComicsWithCharacters(
                characterId,
                limit,
                offset
            )
        } returns emptyList()
        comicsLocalDataSource.getComics(limit = limit, offset = offset, characterId = characterId)
        coEvery { comicsDao.getComicsWithCharacters(characterId, limit, offset) }
    }

    @Test
    fun insert_insertsProperly() = runTest {
        val comicsLocalDataSource = createComicsLocalDataSource()
        val comicsData = mockk<ComicsData>()
        comicsLocalDataSource.insert(comicsData = comicsData)
        coEvery { localComicsMapper.mapToEntity(comicsData) }
    }


}