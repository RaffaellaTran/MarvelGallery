package com.example.rafaellat.marvelgallery.view.main

import com.example.rafaellat.marvelgallery.model.MarvelCharacter

// The View needs to specify the methods used to show the list of characters, show errors, and show the progress bar when
// the View is refreshing

interface MainView {
    var refresh: Boolean
    fun show(items: List<MarvelCharacter>)
    fun showError(error: Throwable)
}