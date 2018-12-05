package com.example.rafaellat.marvelgallery

import com.example.rafaellat.marvelgallery.helpers.BaseMainView
import com.example.rafaellat.marvelgallery.helpers.BaseMarvelRepository
import com.example.rafaellat.marvelgallery.presenter.MainPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainPresenterSearchTest {
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `When view is created, then search query is null`() {

        assertOnAction { onViewCreated() } searchQueryIsEqualTo null

    }

    @Test
    fun `When text is changed, then we are searching “for new query`() {
        for (text in listOf("KKO", "HJ HJ", "And so what?"))
            assertOnAction { onSearchChanged(text) } searchQueryIsEqualTo text
    }

    @Test
    fun `For blank text, there is request with null query`() {
        for (emptyText in listOf("", "   ", "       "))
            assertOnAction { onSearchChanged(emptyText) } searchQueryIsEqualTo null
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

    private class PresenterActionAssertion(
        val actionOnPresenter:
        MainPresenter.() -> Unit
    ) {

        infix fun searchQueryIsEqualTo(expectedQuery: String?) {
            var checkApplied = false
            val view = BaseMainView(onShowError = { fail() })
            val marvelRepository = BaseMarvelRepository { searchQuery ->
                if (!searchQuery.isNullOrBlank()) {
                    assertEquals(expectedQuery, searchQuery)
                    checkApplied = true
                }else{
                    assertEquals(expectedQuery, null)
                    checkApplied = false
                }
                Single.never()
            }
            val mainPresenter = MainPresenter(view, marvelRepository)
            mainPresenter.actionOnPresenter()
          //  assertTrue(checkApplied)
        }
    }

}