package com.example.rafaellat.marvelgallery.helpers

import com.example.rafaellat.marvelgallery.model.MarvelCharacter


object Example {
    val exampleCharacter = MarvelCharacter("ExampleName", "ExampleImageUrl")
    val exampleCharacterList = listOf(
        exampleCharacter,
        MarvelCharacter("Name1", "ImageUrl1"),
        MarvelCharacter("Name2", "ImageUrl2")
    )
}