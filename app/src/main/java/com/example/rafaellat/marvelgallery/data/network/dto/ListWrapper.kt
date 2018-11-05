package com.example.rafaellat.marvelgallery.data.network.dto

//Liswt of occurences
class ListWrapper<T> {
    var items: List<T> = listOf()
}

//Holds the data we need about occurrences
class ComicDto {
    lateinit var name: String
}

// For values that might not be provided, we should set a default value. Values that are mandatory might be prefixed with lateinit instead.
class ImageDto {

    lateinit var path: String
    lateinit var extension: String

    val completeImagePath: String
        get() = "$path.$extension"
}

class CharacterMarvelDto {
    lateinit var name: String
    lateinit var description: String
    lateinit var thumbnail: ImageDto
    var comics: ListWrapper<ComicDto> = ListWrapper()
    var series: ListWrapper<ComicDto> = ListWrapper()
    var stories: ListWrapper<ComicDto> = ListWrapper()
    var events: ListWrapper<ComicDto> = ListWrapper()

    val imageUrl: String
        get() = thumbnail.completeImagePath
}