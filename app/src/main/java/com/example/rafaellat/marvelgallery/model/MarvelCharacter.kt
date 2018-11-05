package com.example.rafaellat.marvelgallery.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.rafaellat.marvelgallery.data.network.dto.CharacterMarvelDto
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize

class MarvelCharacter(
    val name: String,
    val imageUrl: String,
    val description: String,
    val comics: List<String>,
    val series: List<String>,
    val stories: List<String>,
    val events: List<String>
) : Parcelable {
    constructor(dto: CharacterMarvelDto) : this(
        name = dto.name,
        imageUrl = dto.imageUrl,
        description = dto.description,
        comics = dto.comics.items.map { it.name },
        series = dto.comics.items.map { it.name },
        stories = dto.comics.items.map { it.name },
        events = dto.comics.items.map { it.name }
    )
}