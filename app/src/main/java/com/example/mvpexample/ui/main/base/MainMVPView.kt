package com.example.mvpexample.ui.main.base

import com.example.mvpexample.core.mvp.view.MVPView
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.model.ComicsItem

interface MainMVPView : MVPView {
    fun updateCharacters(list: List<CharacterItem>)
    fun setCharacterLoadingStatus(status: Boolean)
    fun showError(exception: Throwable)
    fun updateComics(comics: List<ComicsItem>)
    fun loadThumbnail(image: String)
    fun setComicsLoadingStatus(status: Boolean)
    fun setNoResultViewVisibility(visibility: Boolean)
    fun setCharacterTitle(title: String)
}