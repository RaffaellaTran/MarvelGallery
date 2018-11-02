package com.example.rafaellat.marvelgallery.presenter

import io.reactivex.disposables.CompositeDisposable


//the functionality of collecting subscriptions and of disposing them when the user destroys the view can be treated as common behavior and extracted
abstract class BasePresenter : Presenter {
    protected var subscriptions = CompositeDisposable()
    override fun onViewDestroyed() {
        subscriptions.dispose()
    }
}