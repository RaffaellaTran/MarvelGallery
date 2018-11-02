package com.example.rafaellat.marvelgallery.helpers

import com.example.rafaellat.marvelgallery.data.MarvelRepository
import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(
    val onGetCharacters: () -> Single<List<MarvelCharacter>>
) : MarvelRepository {

    override fun getAllCharacters() = onGetCharacters()
}


