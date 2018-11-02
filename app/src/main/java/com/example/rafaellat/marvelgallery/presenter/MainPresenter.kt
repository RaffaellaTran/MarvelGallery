package com.example.rafaellat.marvelgallery.presenter

import com.example.rafaellat.marvelgallery.data.MarvelRepository
import com.example.rafaellat.marvelgallery.data.applySchedulers
import com.example.rafaellat.marvelgallery.data.plusAssign
import com.example.rafaellat.marvelgallery.data.subscribeBy
import com.example.rafaellat.marvelgallery.view.main.MainView


class MainPresenter(val view: MainView, val repository: MarvelRepository) :
    BasePresenter() { // BasePresenter in case the user leaves the application before loading have finished
    fun onViewCreated() {
        loadCharacters()
    }

    fun onRefresh() {
        loadCharacters()
    }

    private fun loadCharacters() {
        repository.getAllCharacters()
            .applySchedulers()
            .subscribe({ items -> view.show(items) })

        subscriptions += repository.getAllCharacters()
            .applySchedulers()
            .doOnSubscribe { view.refresh = true }
            .doFinally { view.refresh = false }
            .subscribeBy(
                onSuccess = view::show,
                onError = view::showError
            )

//        repository.getAllCharacters() //uses a network operation and it can't run on the main thread if we don't specify
//            .subscribeOn(Schedulers.io()) // we specify that the network request should run in the IO thread
//            .observeOn(AndroidSchedulers.mainThread()) //callbacks should be started on the main thread because we are changing the view in the call back
//            .subscribe({ items -> view.show(items) })
    }
}