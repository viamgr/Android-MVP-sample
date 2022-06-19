package com.example.mvpexample.ui.main.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.mvpexample.R
import com.example.mvpexample.databinding.ActivityMainBinding
import com.example.mvpexample.domain.model.CharacterItem
import com.example.mvpexample.domain.model.ComicsItem
import com.example.mvpexample.ui.main.adapter.CharactersAdapter
import com.example.mvpexample.ui.main.adapter.ComicAdapter
import com.example.mvpexample.ui.main.base.MainMVPInteractor
import com.example.mvpexample.ui.main.base.MainMVPPresenter
import com.example.mvpexample.ui.main.base.MainMVPView
import com.example.mvpexample.ui_common.PaginationScrollListener
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainMVPView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var comicsAdapter: ComicAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    private fun initViews() = with(binding) {

        setSupportActionBar(toolbar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            when {
                abs(verticalOffset) == barLayout.totalScrollRange -> {
                    showThumbnail()
                }
                verticalOffset == 0 -> {
                    hideThumbnail()
                }
                else -> {
                    hideThumbnail()
                }
            }
        })

        toolbar.setOnClickListener {
            expandToolbar()
        }
        rvCharacters.apply {
            charactersAdapter = CharactersAdapter(::onCharacterItemClicked)
            adapter = charactersAdapter
            addOnScrollListener(object :
                PaginationScrollListener(layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    presenter.onLoadMoreCharacterItemsRequested()
                }
            })

        }
        rvComics.apply {
            comicsAdapter = ComicAdapter(::onComicsItemItemClicked)
            adapter = comicsAdapter
            addOnScrollListener(object :
                PaginationScrollListener(layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    presenter.onLoadMoreComicsItemsRequested()
                }
            })
        }
    }

    private fun expandToolbar() {
        binding.appBarLayout.setExpanded(true, true)
    }

    private fun collapseToolbar() {
        binding.appBarLayout.setExpanded(false, true)
    }

    private fun onCharacterItemClicked(characterItem: CharacterItem) {
        collapseToolbar()
        presenter.onCharacterItemClicked(characterItem)
    }

    override fun setCharacterTitle(title: String) {
        setToolbarTitle(getString(R.string.comics, title))
    }

    private fun setToolbarTitle(title: String) {
        binding.collapsingToolbarLayout.title = title
    }

    private fun onComicsItemItemClicked(comicsItem: ComicsItem) {
        collapseToolbar()
    }

    private fun showThumbnail() = with(binding.cvThumbnail) {
        animate().cancel()
        animate().alpha(1f)
    }

    private fun hideThumbnail() = with(binding.cvThumbnail) {
        animate().cancel()
        alpha = 0F
    }

    override fun updateCharacters(list: List<CharacterItem>) {
        charactersAdapter.submitList(list.toMutableList())

    }

    override fun setCharacterLoadingStatus(status: Boolean) {
        binding.pbCharacterProgress.isInvisible = !status
    }

    override fun showError(exception: Throwable) {
        exception.printStackTrace()
        setToolbarTitle(getString(R.string.error))
        Snackbar.make(binding.root, getString(R.string.error), LENGTH_SHORT).show()
    }

    override fun setNoResultViewVisibility(visibility: Boolean) {
        binding.tvNoComics.isInvisible = !visibility

    }

    override fun updateComics(comics: List<ComicsItem>) {
        comicsAdapter.submitList(comics.toMutableList())
    }

    override fun loadThumbnail(image: String) {
        binding.thumbnail.load(image)
    }

    override fun setComicsLoadingStatus(status: Boolean) {
        binding.pbCommitsProgress.isVisible = status
    }

}