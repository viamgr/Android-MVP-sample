package com.example.mvpexample.di

import com.example.mvpexample.ui.main.base.MainMVPInteractor
import com.example.mvpexample.ui.main.base.MainMVPPresenter
import com.example.mvpexample.ui.main.base.MainMVPView
import com.example.mvpexample.ui.main.impl.MainInteractor
import com.example.mvpexample.ui.main.impl.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MainModule {

    @Provides
    fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>):
            MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter
}
