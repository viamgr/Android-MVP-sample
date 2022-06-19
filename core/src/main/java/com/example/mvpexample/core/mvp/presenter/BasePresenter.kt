package com.example.mvpexample.core.mvp.presenter

import androidx.annotation.CallSuper
import com.example.mvpexample.core.mvp.interactor.MVPInteractor
import com.example.mvpexample.core.mvp.view.MVPView
import kotlinx.coroutines.*


abstract class BasePresenter<V : MVPView, I : MVPInteractor>(
    protected var interactor: I?,
    private val coroutineDispatcher: CoroutineDispatcher,
) : MVPPresenter<V, I> {

    private var view: V? = null

    private val coroutineScope = lazy {
        CoroutineScope(coroutineDispatcher)
    }

    @CallSuper
    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        onError(exception)
    }

    protected open fun onError(exception: Throwable) {}

    protected fun launch(callback: suspend () -> Unit) =
        CoroutineScope(coroutineDispatcher + Job()).launch(errorHandler) {
            callback()
        }

    override fun onDetach() {
        coroutineScope.value.cancel()
        view = null
        interactor = null
    }

}