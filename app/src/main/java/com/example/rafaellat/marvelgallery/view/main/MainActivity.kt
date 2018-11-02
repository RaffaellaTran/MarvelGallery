package com.example.rafaellat.marvelgallery.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.example.rafaellat.marvelgallery.R
import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val characters = listOf(
        MarvelCharacter(name = "3-D Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
        MarvelCharacter(
            name = "Abomination (Emil Blonsky)",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // no title
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val categoryItemAdapters = characters
            .map(::CharacterItemAdapter) // creating item adapters from characters using the CharacterItemAdapter
        // constructor reference
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }
}
