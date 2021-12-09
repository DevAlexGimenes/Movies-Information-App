package com.alex.movie.information.app.presentation.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alex.movie.information.app.R
import com.alex.movie.information.app.data.movies.DMoviesInformation
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details_movie_information.*

class DetailsMovieInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie_information)
        supportActionBar?.hide()

        val extras = intent.extras
        if (extras != null) {
            val character = intent.getParcelableExtra<DMoviesInformation>("key")
            setComponents(character!!)
        }
    }

    private fun setComponents(movie: DMoviesInformation) {
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        Glide.with(moviePosterPath).load(IMAGE_BASE + movie.poster).into(moviePosterPath)

        titleMovieInfo.text = movie.title
        descMovieInfo.text = getString(R.string.overview_movie_information, movie.overview)
        releaseDateMovie.text = getString(R.string.released_movie_information, movie.release)

        if (movie.forAdults == true) {
            forAdultInfoMovie.text = getString(R.string.for_adult_true_information)
        } else {
            forAdultInfoMovie.text = getString(R.string.for_adult_false_information)
        }
    }
}