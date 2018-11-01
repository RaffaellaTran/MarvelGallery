package com.example.rafaellat.marvelgallery.view.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

//The class is open instead of abstract because it can be initialized and used without any children. We define children
// to allow us to define custom methods for different lists.

open class RecyclerListAdapter( // 1
    var items: List<AnyItemAdapter> = listOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //The methods are overriding methods of RecyclerView.Adapter, but they also use final modifier to restrict their override in children

    override final fun getItemCount() = items.size

    override final fun getItemViewType(position: Int) = items[position].layoutId

    override final fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return items.first { it.layoutId == layoutId }.onCreateViewHolder(itemView) //layout to distinguish between item
        //types because we cannot use two item adapters with the same layout on the same list
    }

    override final fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }
}

typealias AnyItemAdapter = ItemAdapter<out RecyclerView.ViewHolder> //type alias to simplify the definition of any ItemAdapter