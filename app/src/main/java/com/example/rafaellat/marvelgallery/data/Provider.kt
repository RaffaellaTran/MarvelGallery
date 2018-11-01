package com.example.rafaellat.marvelgallery.data

//mechanism that will generate a standard implementation during normal runtime, but it will also allow us to set a different implementation for testing purposes
// can be done with Dagger or Kodein
abstract class Provider<T> {
    abstract fun creator(): T

    private val instance: T by lazy { creator() }
    var testingInstance: T? = null

    fun get(): T = testingInstance ?: instance
}