package com.example.rafaellat.marvelgallery

import okhttp3.logging.HttpLoggingInterceptor

//function for display logs on the console when we are running the app in debug mode
fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
}