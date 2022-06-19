package com.example.mvpexample.ui.main.impl

import androidx.annotation.VisibleForTesting
import com.example.mvpexample.core.mvp.presenter.BasePresenter
import com.example.mvpexample.core.withResult
import com.example.mvpexample.di.IoDispatcher
import com.example.mvpexample.di.MainDispatcher
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.ui.main.base.MainMVPInteractor
import com.example.mvpexample.ui.main.base.MainMVPPresenter
import com.example.mvpexample.ui.main.base.MainMVPView
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityScoped
class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(
    interactor: I,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : BasePresenter<V, I>(
    interactor = interactor,
    coroutineDispatcher = coroutineDispatcher
), MainMVPPresenter<V, I> {

    @VisibleForTesting
    lateinit var selectedCharacter: CharacterItem
    private val characters = mutableListOf<CharacterItem>()
    private val comics = mutableListOf<ComicsItem>()

    @Volatile
    private var isGettingCharacters = false

    @Volatile
    private var isGettingComics = false

    @Volatile
    private var charactersListReachedEnd = false

    @Volatile
    private var comicsListReachedEnd = false

    override fun onAttach(view: V?) {
        super.onAttach(view)
        loadCharacters(0)
    }

    private fun loadCharacters(offset: Int) = launch {
        if (!isGettingCharacters && !charactersListReachedEnd) {
            setCharacterLoadingStatus(true)
            interactor?.getCharacters(offset = offset, limit = LIMIT)
                ?.withResult(
                    onSuccess = {
                        setCharacterLoadingStatus(false)
                        if (offset == 0 && it.data.isNotEmpty()) {
                            loadComics(it.data.first(), 0)
                        }
                        if (it.isReachedEnd) {
                            charactersListReachedEnd = true
                        }
                        characters.addAll(it.data)
                        withContext(mainDispatcher) {
                            getView()?.updateCharacters(characters)
                        }
                    },
                    onFailure = {
                        setCharacterLoadingStatus(false)
                        getView()?.showError(it)
                    })
        }
    }

    private suspend fun setCharacterLoadingStatus(loading: Boolean) = withContext(mainDispatcher) {
        isGettingCharacters = loading
        getView()?.setCharacterLoadingStatus(loading)
    }

    private suspend fun setComicsLoadingStatus(loading: Boolean) = withContext(mainDispatcher) {
        isGettingComics = loading
        getView()?.setComicsLoadingStatus(loading)
    }

    override fun onError(exception: Throwable) {
        getView()?.showError(exception)
    }

    override fun onLoadMoreCharacterItemsRequested() {
        loadCharacters(characters.size)
    }

    override fun onLoadMoreComicsItemsRequested() {
        loadComics(selectedCharacter, comics.size)
    }

    override fun onCharacterItemClicked(characterItem: CharacterItem) {
        comics.clear()
        comicsListReachedEnd = false
        isGettingComics = false

        setToolBarText(characterItem.title)
        setNoResultViewVisibility(false)
        resetComicsView()
        loadComics(characterItem, 0)
    }

    private fun setToolBarText(title: String) {
        getView()?.setCharacterTitle(title)
    }

    private fun loadComics(
        characterItem: CharacterItem,
        offset: Int
    ) = launch {
        selectedCharacter = characterItem

        setToolBarText(characterItem.title)
        getView()?.loadThumbnail(characterItem.image)

        if (!isGettingComics && !comicsListReachedEnd) {
            setComicsLoadingStatus(true)

            interactor
                ?.getComics(characterId = characterItem.id, limit = LIMIT, offset = offset)
                ?.withResult(
                    onSuccess = {
                        setComicsLoadingStatus(false)
                        if (it.isReachedEnd) {
                            comicsListReachedEnd = true
                        }
                        comics.addAll(it.data)
                        setNoResultViewVisibility(it.data.isEmpty())
                        resetComicsView()
                    },
                    onFailure = {
                        setComicsLoadingStatus(false)
                        getView()?.showError(it)
                    })

        }
    }

    private fun setNoResultViewVisibility(visibility: Boolean) = launch {
        withContext(mainDispatcher) {
            getView()?.setNoResultViewVisibility(visibility)
        }
    }

    private fun resetComicsView() = launch {
        withContext(mainDispatcher) {
            getView()?.updateComics(comics)
        }
    }

    companion object {
        const val LIMIT = 5
    }
}