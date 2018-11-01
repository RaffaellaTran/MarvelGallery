package com.example.rafaellat.marvelgallery.view.main

import com.example.rafaellat.marvelgallery.view.common.AnyItemAdapter
import com.example.rafaellat.marvelgallery.view.common.RecyclerListAdapter

//Adapter for the character list
class MainListAdapter(items: List<AnyItemAdapter>) : RecyclerListAdapter(items) {

    fun add(itemAdapter: AnyItemAdapter) {
        items += itemAdapter

        val index = items.indexOf(itemAdapter)
        if (index == -1) return
        notifyItemInserted(index)

    }

    fun delete(itemAdapter: AnyItemAdapter) {
        val index = items.indexOf(itemAdapter)
        if (index == -1) return
        items -= itemAdapter
        notifyItemRemoved(index)
    }
}