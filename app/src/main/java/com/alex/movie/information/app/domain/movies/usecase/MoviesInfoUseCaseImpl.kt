package com.alex.movie.information.app.domain.movies.usecase

import com.alex.movie.information.app.data.movies.DMoviesInformation
import com.alex.movie.information.app.domain.movies.repository.MoviesInfoRepository

class MoviesInfoUseCaseImpl(
    private val movieRepository: MoviesInfoRepository
): MoviesInfoUseCase {

    override suspend fun getPopularMovies(): List<DMoviesInformation> {
       return movieRepository.getPopularMovies()
    }

    override suspend fun getNowPlayingMovies(): List<DMoviesInformation> {
        return movieRepository.getNowPlayingMovies()
    }

    override suspend fun getTopRatedMovies(): List<DMoviesInformation> {
        return movieRepository.getTopRatedMovies()
    }
}