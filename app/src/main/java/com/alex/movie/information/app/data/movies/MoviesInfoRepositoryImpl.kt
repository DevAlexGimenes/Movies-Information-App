package com.alex.movie.information.app.data.movies

import com.alex.movie.information.app.data.api.MovieInfoRemoteSource
import com.alex.movie.information.app.domain.movies.repository.MoviesInfoRepository

class MoviesInfoRepositoryImpl(
    private val movieRemoteSource: MovieInfoRemoteSource
) : MoviesInfoRepository {

    override suspend fun getPopularMovies(): List<DMoviesInformation> {
        return movieRemoteSource.getPopularMovieList().movies
    }

    override suspend fun getNowPlayingMovies(): List<DMoviesInformation> {
        return movieRemoteSource.getNowPlayingMovieList().movies
    }

    override suspend fun getTopRatedMovies(): List<DMoviesInformation> {
        return movieRemoteSource.getTopRatedMovies().movies
    }
}