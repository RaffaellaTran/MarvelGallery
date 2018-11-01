package com.example.rafaellat.marvelgallery.view.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ItemAdapter<T : RecyclerView.ViewHolder>
    (@LayoutRes open val layoutId: Int) {

    abstract fun onCreateViewHolder(itemView: View): T

    @Suppress("UNCHECKED_CAST") // hide the warning while we know that we can securely cast in this situation.
    fun bindViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as T).onBindViewHolder()
    }

    abstract fun T.onBindViewHolder() //set all the values on item view
}