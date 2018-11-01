package com.example.rafaellat.marvelgallery.data.network.dto

class DataContainer<T> {
    var results: T? = null
}

class DataWrapper<T> {
    var data: DataContainer<T>? = null
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
    lateinit var thumbnail: ImageDto

    val imageUrl: String
        get() = thumbnail.completeImagePath
}