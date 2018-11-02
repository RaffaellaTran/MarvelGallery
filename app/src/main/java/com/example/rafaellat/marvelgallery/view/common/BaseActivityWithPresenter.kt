package com.example.rafaellat.marvelgallery.view.common

import android.support.v7.app.AppCompatActivity
import com.example.rafaellat.marvelgallery.presenter.Presenter

// Class holds the presenter behind the Presenter interface and call the onViewDestroyed method when the view is destroyed
abstract class BaseActivityWithPresenter : AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}