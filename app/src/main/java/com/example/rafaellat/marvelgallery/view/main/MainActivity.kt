package com.example.rafaellat.marvelgallery.view.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.example.rafaellat.marvelgallery.R
import com.example.rafaellat.marvelgallery.character.CharacterProfileActivity
import com.example.rafaellat.marvelgallery.data.MarvelRepository
import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import com.example.rafaellat.marvelgallery.presenter.MainPresenter
import com.example.rafaellat.marvelgallery.view.common.BaseActivityWithPresenter
import com.example.rafaellat.marvelgallery.view.common.addOnTextChangedListener
import com.example.rafaellat.marvelgallery.view.common.bindToSwipeRefresh
import com.example.rafaellat.marvelgallery.view.common.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(),
    MainView { // activity should extend BaseActivityWithPresenter and implement MainView

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView) // function for bind a property with the visibility of the swipe refresh
    override val presenter by lazy { MainPresenter(this, MarvelRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() } // pass events to the presenter using its methods
        search_view.addOnTextChangedListener {
            onTextChanged({ text, _, _, _ ->
                presenter.onSearchChanged(text)
            })
        }
        presenter.onViewCreated()
    }

    override fun show(items: List<MarvelCharacter>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }

    private fun createCategoryItemAdapter(character: MarvelCharacter) = CharacterItemAdapter(character,
        { showHeroProfile(character) })

    private fun showHeroProfile(character: MarvelCharacter) {
        CharacterProfileActivity.start(this, character)
    }
}
