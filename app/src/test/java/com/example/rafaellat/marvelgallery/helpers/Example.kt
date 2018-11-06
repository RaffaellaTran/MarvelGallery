package com.example.rafaellat.marvelgallery.helpers

import com.example.rafaellat.marvelgallery.model.MarvelCharacter


object Example {
    val list = listOf<String>()
    val exampleCharacter =
        MarvelCharacter("ExampleName", "ExampleImageUrl", "ExampleDescription", list, list, list, list)
    val exampleCharacterList = listOf(
        exampleCharacter,
        MarvelCharacter("Name1", "ImageUrl1", "Description1", list, list, list, list),
        MarvelCharacter("Name2", "ImageUrl2", "Description1", list, list, list, list)
    )
}

