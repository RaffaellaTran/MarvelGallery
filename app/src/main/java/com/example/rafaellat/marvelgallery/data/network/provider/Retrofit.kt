package com.example.rafaellat.marvelgallery.data.network.provider

import com.example.rafaellat.marvelgallery.makeLoggingInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy { makeRetrofit() }

private fun makeRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("http://gateway.marvel.com/v1/public/")
    .client(makeHttpClient())
    .addConverterFactory(GsonConverterFactory.create(Gson())) // Converter for JSON serialization and deserialization using the GSON
.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // add a converter for allowing RxJava2 types as observables for returnning values from network requests
    .build()

private fun makeHttpClient() = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS) // custom interceptior
    .readTimeout(60, TimeUnit.SECONDS) // 4
    .addInterceptor(makeHeadersInterceptor()) // add standard headers for each request
    .addInterceptor(makeAddSecurityQueryInterceptor()) // 6
    .addInterceptor(makeLoggingInterceptor()) // 7
    .build()