package com.example.rafaellat.marvelgallery.data

import com.example.rafaellat.marvelgallery.data.network.MarvelApi
import com.example.rafaellat.marvelgallery.data.network.provider.retrofit
import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import io.reactivex.Single


class MarvelRepositoryImpl : MarvelRepository {

    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = api.getCharacters(
        offset = 0,
        searchQuery =searchQuery,
        limit = elementsOnListLimit
    ).map {
        it.data?.results.orEmpty().map(::MarvelCharacter) //get a list of DTO elements and map it into MarvelCharacter using a constructor reference
    }

    companion object {
        const val elementsOnListLimit = 50
    }
}
