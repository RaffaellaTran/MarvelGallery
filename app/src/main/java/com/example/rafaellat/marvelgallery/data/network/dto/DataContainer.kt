package com.example.rafaellat.marvelgallery.data.network.dto

class DataContainer<T> {
    var results: T? = null
}

class DataWrapper<T> {
    var data: DataContainer<T>? = null
}
