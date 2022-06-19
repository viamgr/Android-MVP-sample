package com.example.mvpexample.ui.main.base

import com.example.mvpexample.core.mvp.presenter.MVPPresenter
import com.example.mvpexample.domain.model.CharacterItem

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {
    fun onLoadMoreCharacterItemsRequested()
    fun onCharacterItemClicked(characterItem: CharacterItem)
    fun onLoadMoreComicsItemsRequested()

}