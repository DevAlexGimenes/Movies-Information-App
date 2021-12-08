package com.alex.movie.information.app.data.movies

import com.alex.movie.information.app.data.api.MovieInfoRemoteSource
import com.alex.movie.information.app.domain.movies.repository.MoviesInfoRepository
import kotlinx.coroutines.delay

class MoviesInfoRepositoryImpl(
    private val movieRemoteSource: MovieInfoRemoteSource
): MoviesInfoRepository {

    override suspend fun getPopularMovies(): List<DMoviesInformation> {
        delay(3000)
        return movieRemoteSource.getPopularMovieList().movies
    }

    override suspend fun getNowPlayingMovies(): List<DMoviesInformation> {
        delay(3000)
        return movieRemoteSource.getNowPlayingMovieList().movies
    }

    override suspend fun getTopRatedMovies(): List<DMoviesInformation> {
        delay(3000)
        return movieRemoteSource.getTopRatedMovies().movies
    }
}