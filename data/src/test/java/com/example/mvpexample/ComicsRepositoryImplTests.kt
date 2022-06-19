package com.example.mvpexample

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.datasource.comics.CharacterComicsLocal
import com.example.mvpexample.data.datasource.comics.ComicsLocal
import com.example.mvpexample.data.datasource.comics.ComicsRemote
import com.example.mvpexample.data.mapper.ComicsMapper
import com.example.mvpexample.data.model.CharacterComicsData
import com.example.mvpexample.data.model.ComicsData
import com.example.mvpexample.data.repository.ComicsRepositoryImpl
import com.example.mvpexample.domain.model.ComicsItem
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ComicsRepositoryImplTests {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var comicsMapper: ComicsMapper

    @RelaxedMockK
    lateinit var comicsRemote: ComicsRemote

    @RelaxedMockK
    lateinit var comicsLocal: ComicsLocal

    @RelaxedMockK
    lateinit var characterComicsLocal: CharacterComicsLocal

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createComicsRepository(): ComicsRepositoryImpl {
        return ComicsRepositoryImpl(
            comicsMapper = comicsMapper,
            comicsRemote = comicsRemote,
            comicsLocal = comicsLocal,
            characterComicsLocal = characterComicsLocal
        )
    }

    @Test
    fun getComics_ensureOfflineFirst_worksProperly() = runTest {
        val characterId = 34283L
        val limit = 15415
        val offset = 48892

        val comicsItem = mockk<ComicsItem>()
        val data = listOf<ComicsData>(mockk())
        coEvery {
            comicsLocal.getComics(
                characterId,
                limit,
                offset
            )
        } returns PagingResponse(true, data)

        coEvery {
            comicsMapper.map(any())
        } returns comicsItem

        val comicsRepository = createComicsRepository()

        val result = comicsRepository.getComics(characterId, offset, limit)
        coVerify(exactly = 1) {
            comicsLocal.getComics(
                characterId,
                limit,
                offset
            )
        }
        coVerify(exactly = 0) {
            comicsRemote.getComics(
                characterId,
                limit,
                offset
            )
        }
        Assert.assertEquals(comicsItem, result.data.first())
    }

    @Test
    fun getComics_ensureOnlineLast_worksProperly() = runTest {
        val characterId = 34283L
        val limit = 15415
        val offset = 48892
        val comicsId = 23423L
        val comicsItem =
            ComicsItem(comicsId, title = "title", image = "image", description = "description")
        val comicsData =
            ComicsData(comicsId, name = "title", thumbnail = "image", description = null)

        val characterComicsCapturingSlot: CapturingSlot<CharacterComicsData> = slot()
        coEvery {
            val characterComicsData = capture(characterComicsCapturingSlot)
            characterComicsLocal.insert(characterComicsData)
        } returns Unit

        coEvery {
            comicsLocal.getComics(
                characterId,
                limit,
                offset
            )
        } returns PagingResponse(true, emptyList())

        coEvery {
            comicsRemote.getComics(
                characterId,
                limit,
                offset
            )
        } returns PagingResponse(true, listOf(comicsData))

        coEvery {
            comicsMapper.map(any())
        } returns comicsItem

        val comicsRepository = createComicsRepository()

        val result = comicsRepository.getComics(characterId, offset, limit)
        coVerify(exactly = 1) {
            comicsLocal.getComics(
                characterId,
                limit,
                offset
            )
        }
        coVerify(exactly = 1) {
            comicsRemote.getComics(
                characterId,
                limit,
                offset
            )
        }
        coVerify(exactly = 1) {
            comicsLocal.insert(comicsData)
        }

        Assert.assertEquals(comicsItem, result.data.first())
        Assert.assertEquals(characterId, characterComicsCapturingSlot.captured.characterId)
        Assert.assertEquals(comicsId, characterComicsCapturingSlot.captured.comicsId)
    }


}