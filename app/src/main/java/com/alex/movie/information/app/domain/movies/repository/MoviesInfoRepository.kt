package com.alex.movie.information.app.domain.movies.repository

import com.alex.movie.information.app.data.movies.DMoviesInformation

interface MoviesInfoRepository {

    suspend fun getPopularMovies() : List<DMoviesInformation>

    suspend fun getNowPlayingMovies() : List<DMoviesInformation>

    suspend fun getTopRatedMovies() : List<DMoviesInformation>
}