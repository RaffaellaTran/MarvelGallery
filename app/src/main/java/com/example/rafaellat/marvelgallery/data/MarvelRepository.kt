package com.example.rafaellat.marvelgallery.data

import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {
    fun getAllCharacters(): Single<List<MarvelCharacter>>

    companion object : Provider<MarvelRepository>() {
        override fun creator() = MarvelRepositoryImpl()
    }
}


