package com.example.rafaellat.marvelgallery.data

import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {

    //search query as a getAllCharacters parameter
    fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>>

    companion object : Provider<MarvelRepository>() {
        override fun creator() = MarvelRepositoryImpl()
    }
}


