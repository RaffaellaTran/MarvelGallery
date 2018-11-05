package com.example.rafaellat.marvelgallery.character

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.rafaellat.marvelgallery.R
import com.example.rafaellat.marvelgallery.model.MarvelCharacter
import com.example.rafaellat.marvelgallery.view.common.extra
import com.example.rafaellat.marvelgallery.view.common.getIntent
import com.example.rafaellat.marvelgallery.view.common.loadImage
import kotlinx.android.synthetic.main.activity_character_profile.*

class CharacterProfileActivity : AppCompatActivity() {

    // @get:Arg val character: MarvelCharacter by argExtra()
    val character: MarvelCharacter by extra(CHARACTER_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_profile)
        setUpToolbar()

        supportActionBar?.title = character.name
        description_view.text = character.description
        occurrances_view.text = makeOccurrencesText()
        header_view.loadImage(character.imageUrl, centerCropped = true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when {
        item.itemId == android.R.id.home -> onBackPressed().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun makeOccurrencesText(): String =
        "" //a single expression function that uses addList to append an initially
            // empty string with the next lists that we want to display
            .addList(R.string.occurrences_comics_list_introduction, character.comics)
            .addList(R.string.occurrences_series_list_introduction, character.series)
            .addList(R.string.occurrences_stories_list_introduction, character.stories)
            .addList(R.string.occurrences_events_list_introduction, character.events)

    private fun String.addList(introductionTextId: Int, list: List<String>): String { // a member extension function.
        // It returns a string unchanged if the provided list is empty, or it returns a string appended with the introduction
        // text and the list of elements with bullets.
        if (list.isEmpty()) return this
        val introductionText = getString(introductionTextId)
        val listText = list.joinToString(
            transform =
            { " $bullet $it" }, separator = "\n"
        )
        return this + "$introductionText\n$listText\n\n"
    }

    companion object {
        private const val bullet = '\u2022' // character for the list bullet
        private const val CHARACTER_ARG =
            "com.sample.marvelgallery.view.character.CharacterProfileActivity.CharacterArgKey"

        fun start(context: Context, character: MarvelCharacter) {
            val intent = context
                .getIntent<CharacterProfileActivity>()
                .apply { putExtra(CHARACTER_ARG, character) }
            context.startActivity(intent)
        }
    }
}
