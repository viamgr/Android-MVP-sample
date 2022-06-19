package com.example.mvpexample.core.mvp.presenter

import com.example.mvpexample.core.mvp.interactor.MVPInteractor
import com.example.mvpexample.core.mvp.view.MVPView


interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}