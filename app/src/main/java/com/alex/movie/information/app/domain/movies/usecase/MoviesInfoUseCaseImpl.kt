package com.alex.movie.information.app.domain.movies.usecase

import com.alex.movie.information.app.data.movies.DMoviesInformation
import com.alex.movie.information.app.domain.movies.repository.MoviesInfoRepository
import kotlinx.coroutines.delay

class MoviesInfoUseCaseImpl(
    private val movieRepository: MoviesInfoRepository
): MoviesInfoUseCase {

    override suspend fun getPopularMovies(): List<DMoviesInformation> {
        delay(2000)
       return movieRepository.getPopularMovies()
    }

    override suspend fun getNowPlayingMovies(): List<DMoviesInformation> {
        delay(2000)
        return movieRepository.getNowPlayingMovies()
    }

    override suspend fun getTopRatedMovies(): List<DMoviesInformation> {
        delay(2000)
        return movieRepository.getTopRatedMovies()
    }
}