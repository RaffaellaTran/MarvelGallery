package com.example.rafaellat.marvelgallery.view.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.rafaellat.marvelgallery.R
import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import com.example.rafaellat.marvelgallery.view.common.ItemAdapter
import com.example.rafaellat.marvelgallery.view.common.bindView
import com.example.rafaellat.marvelgallery.view.common.loadImage

class CharacterItemAdapter(
    val character: MarvelCharacter, // constructor
    val clicked: (MarvelCharacter)-> Unit
) : ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() { // used to set up views. It was defined as an abstract member extension
        // function in ItemAdapter and, thanks to that, we can now use textView and imageView explicitly inside its body.

        textView.text = character.name
        imageView.loadImage(character.imageUrl) // 3
        itemView.setOnClickListener { clicked(character) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView by bindView<TextView>(R.id.textView) // binding properties to view elements
        val imageView by bindView<ImageView>(R.id.imageView)
    }
}