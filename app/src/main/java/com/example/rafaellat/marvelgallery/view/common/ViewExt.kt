package com.example.rafaellat.marvelgallery.view.common

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(photoUrl: String, centerCropped: Boolean = false) {
    Glide.with(context)
        .load(photoUrl)
        .apply { if (centerCropped) centerCrop() }
        .into(this)
}
