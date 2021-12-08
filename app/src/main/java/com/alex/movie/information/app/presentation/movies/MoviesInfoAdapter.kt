package com.alex.movie.information.app.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.movie.information.app.R
import com.alex.movie.information.app.data.movies.DMoviesInformation
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item_poster_path.view.*

class MoviesInfoAdapter(
private var movies: List<DMoviesInformation>,
private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MoviesInfoAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: DMoviesInformation) {
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.moviePosterPath)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_poster_path, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(movie)
        }
    }

    fun updateList(newList: List<DMoviesInformation>) {
        movies = newList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClicked(movie: DMoviesInformation)
    }
}