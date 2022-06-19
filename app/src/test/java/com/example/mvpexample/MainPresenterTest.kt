package com.example.mvpexample

import com.example.mvpexample.android_test_shared.CoroutineRule
import com.example.mvpexample.core.PagingResponse
import com.example.mvpexample.core.Result
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.ui.main.base.MainMVPInteractor
import com.example.mvpexample.ui.main.base.MainMVPView
import com.example.mvpexample.ui.main.impl.MainPresenter
import com.example.mvpexample.ui.main.impl.MainPresenter.Companion.LIMIT
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainPresenterTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var interactor: MainMVPInteractor

    @RelaxedMockK
    lateinit var view: MainMVPView

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        clearAllMocks()
        MockKAnnotations.init(this)

    }

    private fun createPresenter(): MainPresenter<MainMVPView, MainMVPInteractor> {
        return MainPresenter(interactor, testDispatcher, testDispatcher)
    }


    @Test
    fun onAttachView_SetProperly() {
        val presenter = createPresenter()
        presenter.onAttach(view)
        assertEquals(view, presenter.getView())
        coVerify(exactly = 1) { interactor.getCharacters(offset = 0, limit = any()) }
    }

    @Test
    fun onDestroyView_CleansFine() {
        val presenter = createPresenter()
        presenter.onAttach(view)
        presenter.onDetach()
        assertEquals(null, presenter.getView())
    }

    @Test
    fun onAttach_LoadCharacters() {
        val result =
            Result.Success<PagingResponse<CharacterItem>>(PagingResponse(true, listOf(mockk())))
        coEvery { interactor.getCharacters(any(), any()) } returns result

        val presenter = createPresenter()
        presenter.onAttach(view)

        coVerify(exactly = 1) { interactor.getCharacters(offset = 0, limit = any()) }
        coVerify(exactly = 1) { presenter.getView()?.updateCharacters(result.data.data) }
        verify(exactly = 1) { presenter.getView()?.setCharacterLoadingStatus(true) }
    }

    @Test
    fun onClickCharacterItem_LoadComics() = runTest {
        val characterItem = CharacterItem(1, "title", "image")
        val comicsItem = ComicsItem(1, "title", "description", "image")
        val result =
            Result.Success(PagingResponse(false, listOf(comicsItem)))
        coEvery { interactor.getComics(characterItem.id, any(), any()) } returns result
        coEvery { interactor.getCharacters(any(), any()) } returns mockk()

        val presenter = createPresenter()
        presenter.onAttach(view)

        presenter.onCharacterItemClicked(characterItem)

        coVerify(exactly = 1) {
            interactor.getComics(
                characterId = characterItem.id,
                offset = 0,
                limit = any()
            )
        }
        verifyOrder {
            presenter.getView()?.updateComics(any())
            presenter.getView()?.loadThumbnail(characterItem.image)
            presenter.getView()?.setComicsLoadingStatus(true)
            presenter.getView()?.setComicsLoadingStatus(false)
            presenter.getView()?.updateComics(result.data.data)
        }
    }

    @Test
    fun onLoadMoreCharacterItemsRequested_loadCharactersOneTime_whenReachEnd() = runTest {
        val result =
            Result.Success<PagingResponse<CharacterItem>>(PagingResponse(true, (0..LIMIT).map {
                mockk()
            }))
        coEvery { interactor.getCharacters(any(), any()) } returns result

        val presenter = createPresenter()
        presenter.onAttach(view)
        presenter.onLoadMoreCharacterItemsRequested()
        coVerify(exactly = 1) { interactor.getCharacters(offset = 0, limit = LIMIT) }
    }

    @Test
    fun onLoadMoreCharacterItemsRequested_loadCharacterTwoTime_whenNotReachEnd() = runTest {
        val result =
            Result.Success<PagingResponse<CharacterItem>>(
                PagingResponse(
                    false,
                    (0 until LIMIT).map {
                        mockk()
                    })
            )
        coEvery { interactor.getCharacters(any(), any()) } returns result

        val presenter = createPresenter()
        presenter.onAttach(view)
        presenter.onLoadMoreCharacterItemsRequested()

        coVerifyOrder {
            interactor.getCharacters(offset = 0, limit = LIMIT)
            interactor.getCharacters(offset = LIMIT, limit = LIMIT)
        }
    }

    @Test
    fun onLoadMoreComicsItemsRequested_updatePaging() = runTest {
        val characterItem = CharacterItem(1, "title", "image")
        val result =
            Result.Success<PagingResponse<ComicsItem>>(PagingResponse(false, (0 until LIMIT).map {
                mockk()
            }))

        coEvery { interactor.getComics(characterItem.id, any(), any()) } returns result

        val presenter = createPresenter()
        presenter.onAttach(view)
        presenter.selectedCharacter = characterItem
        presenter.onLoadMoreComicsItemsRequested()
        coVerify(exactly = 1) {
            interactor.getComics(
                characterId = characterItem.id,
                offset = 0,
                limit = LIMIT
            )
        }
        coVerify(exactly = 1) { view.updateComics(result.data.data) }

        val listCapture: CapturingSlot<List<ComicsItem>> = slot()

        every {
            val comics = capture(listCapture)
            view.updateComics(comics)
        }

        presenter.onLoadMoreComicsItemsRequested()
        coVerify(exactly = 1) {
            interactor.getComics(
                characterId = characterItem.id,
                offset = LIMIT,
                limit = LIMIT
            )
        }
        assertEquals(2 * LIMIT, listCapture.captured.size)
    }

}