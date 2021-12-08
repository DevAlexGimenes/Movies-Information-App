package com.alex.movie.information.app.domain.movies.usecase

import com.alex.movie.information.app.data.movies.DMoviesInformation

interface MoviesInfoUseCase {

    suspend fun getPopularMovies() : List<DMoviesInformation>

    suspend fun getNowPlayingMovies() : List<DMoviesInformation>

    suspend fun getTopRatedMovies() : List<DMoviesInformation>
}