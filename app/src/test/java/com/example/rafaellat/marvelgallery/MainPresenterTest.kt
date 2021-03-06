package com.example.rafaellat.marvelgallery

import com.example.rafaellat.marvelgallery.helpers.BaseMainView
import com.example.rafaellat.marvelgallery.helpers.BaseMarvelRepository
import com.example.rafaellat.marvelgallery.helpers.Example
import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import com.example.rafaellat.marvelgallery.presenter.MainPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier") // Descriptive names. Warnings are hidden by Suppression
class MainPresenterTest {

    //To not test on a new thread due to synchronization assertions, it is necessary override schedulers before unit tests
    // to make everything run on the same thread
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `After view was created, list of characters is loaded and displayed`() {
        assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
    }

    @Test
    fun `New list is shown after view was refreshed`() {
        assertOnAction { onRefresh() }.thereIsSameListDisplayed()
    }

    @Test
    fun `When API returns error, it is displayed on view`() {
        // Given
        val someError = Error()
        var errorDisplayed: Throwable? = null
        val view = BaseMainView(
            onShow = { _ -> fail() },
            onShowError = { errorDisplayed = it }
        )
        val marvelRepository = BaseMarvelRepository { Single.error(someError) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        // When
        mainPresenter.onViewCreated()
        // Then
        assertEquals(someError, errorDisplayed)
    }

    //We expect refresh to be displayed during network requests and when elements are shown, but not once processing had finished.

    @Test
    fun `When presenter is waiting for response, refresh is displayed`() {
        // Given
        val view = BaseMainView(refresh = false)
        val marvelRepository = BaseMarvelRepository(
            onGetCharacters = {
                Single.fromCallable {
                    // Then
                    assertTrue(view.refresh)
                    Example.exampleCharacterList
                }
            }
        )
        val mainPresenter = MainPresenter(view, marvelRepository)
        view.onShow = { _ ->
            // Then
            assertTrue(view.refresh)
        }
        // When
        mainPresenter.onViewCreated()
        // Then
        assertFalse(view.refresh)
    }
}

private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

private class PresenterActionAssertion
    (val actionOnPresenter: MainPresenter.() -> Unit) {

    fun thereIsSameListDisplayed() {
        // Given
        var displayedList: List<MarvelCharacter>? = null

        val view = BaseMainView(
            onShow = { items -> displayedList = items },
            onShowError = { fail() }
        )
        val marvelRepository = BaseMarvelRepository(
            onGetCharacters =
            { Single.just(Example.exampleCharacterList) }
        )

        val mainPresenter = MainPresenter(view, marvelRepository)

        // When
        mainPresenter.actionOnPresenter()

        // Then
        assertEquals(Example.exampleCharacterList, displayedList)
    }
}


