package com.example.rafaellat.marvelgallery.model

import com.example.rafaellat.marvelgallery.data.network.dto.CharacterMarvelDto

class MarvelCharacter(
    val name: String,
    val imageUrl: String
) {
    constructor(dto: CharacterMarvelDto) : this(
        name = dto.name,
        imageUrl = dto.imageUrl
    )
}