package com.alex.movie.information.app.presentation.movies.now.playing

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alex.movie.information.app.R
import com.alex.movie.information.app.data.movies.DMoviesInformation
import com.alex.movie.information.app.presentation.home.HomeActivity
import com.alex.movie.information.app.presentation.movies.DetailsMovieInformationActivity
import com.alex.movie.information.app.presentation.movies.MoviesInfoAdapter
import kotlinx.android.synthetic.main.activity_now_playing_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayingMoviesActivity : AppCompatActivity(), MoviesInfoAdapter.OnItemClickListener {

    private val viewModel: NowPlayingMoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_playing_movies)

        supportActionBar?.hide()
        prepareContent()
        prepareViews()
    }


    private fun prepareViews() {
        toolbar.title = getString(R.string.title_now_playing_movies)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            onNavigateToHome()
        }
    }

    private fun prepareContent() {
        val movieAdapter = MoviesInfoAdapter(emptyList(), this)
        val progressDialog = ProgressDialog(this)

        progressDialog.setMessage("Loading Movies...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        rvMoviesInfoList.layoutManager = GridLayoutManager(this, 3)
        rvMoviesInfoList.setHasFixedSize(true)
        rvMoviesInfoList.adapter = movieAdapter

        viewModel.getMoviesInfoCoroutines()
        viewModel.moviesInfo().observe(this, Observer {
            progressDialog.dismiss()
            movieAdapter.updateList(it)
        })
    }

    private fun onNavigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onItemClicked(movie: DMoviesInformation) {
        val intent = Intent(this, DetailsMovieInformationActivity::class.java)
        intent.putExtra("key", movie)
        startActivity(intent)
    }
}