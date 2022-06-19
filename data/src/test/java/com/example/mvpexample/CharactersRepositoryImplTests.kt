package com.example.mvpexample

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.data.datasource.character.CharacterLocal
import com.example.mvpexample.data.datasource.character.CharacterRemote
import com.example.mvpexample.data.mapper.CharacterMapper
import com.example.mvpexample.data.model.CharacterData
import com.example.mvpexample.data.repository.CharactersRepositoryImpl
import com.example.mvpexample.domain.model.CharacterItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CharactersRepositoryImplTests {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var characterMapper: CharacterMapper

    @RelaxedMockK
    lateinit var characterRemote: CharacterRemote

    @RelaxedMockK
    lateinit var characterLocal: CharacterLocal

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createCharacterRepository(): CharactersRepositoryImpl {
        return CharactersRepositoryImpl(
            characterMapper = characterMapper,
            characterRemote = characterRemote,
            characterLocal = characterLocal
        )
    }

    @Test
    fun getCharacter_ensureOfflineFirst_worksProperly() = runTest {
        val limit = 15415
        val offset = 48892

        val characterItem = mockk<CharacterItem>()
        val data = listOf<CharacterData>(mockk())
        coEvery {
            characterLocal.getCharacters(
                limit,
                offset
            )
        } returns PagingResponse(true, data)

        coEvery {
            characterMapper.map(any())
        } returns characterItem

        val characterRepository = createCharacterRepository()

        val result = characterRepository.getCharacters(offset, limit)
        coVerify(exactly = 1) {
            characterLocal.getCharacters(
                limit,
                offset
            )
        }
        coVerify(exactly = 0) {
            characterRemote.getCharacters(
                limit,
                offset
            )
        }
        Assert.assertEquals(characterItem, result.data.first())
    }

    @Test
    fun getCharacter_ensureOnlineLast_worksProperly() = runTest {
        val limit = 15415
        val offset = 48892
        val characterId: Long = 10
        val characterItem = CharacterItem(title = "title", image = "image", id = characterId)
        val characterData =
            CharacterData(
                name = "title",
                thumbnail = "image",
                description = "description",
                characterId = characterId
            )

        coEvery {
            characterLocal.getCharacters(
                limit,
                offset
            )
        } returns PagingResponse(true, emptyList())

        coEvery {
            characterRemote.getCharacters(
                limit,
                offset
            )
        } returns PagingResponse(true, listOf(characterData))

        coEvery {
            characterMapper.map(any())
        } returns characterItem

        val characterRepository = createCharacterRepository()

        val result = characterRepository.getCharacters(offset, limit)
        coVerify(exactly = 1) {
            characterLocal.getCharacters(
                limit,
                offset
            )
        }
        coVerify(exactly = 1) {
            characterRemote.getCharacters(
                limit,
                offset
            )
        }
        coVerify(exactly = 1) {
            characterLocal.insert(characterData)
        }

        Assert.assertEquals(characterItem, result.data.first())
        Assert.assertEquals(characterId, result.data.first().id)
    }

}