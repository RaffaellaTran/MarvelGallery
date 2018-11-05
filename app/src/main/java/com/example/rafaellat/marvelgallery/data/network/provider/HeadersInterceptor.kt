package com.example.rafaellat.marvelgallery.data.network.provider

import okhttp3.Interceptor

//This class is used to add standard headers for each request
fun makeHeadersInterceptor() = Interceptor { chain ->
    // interceptor is SAM
    chain.proceed(
        chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build()
    )
}