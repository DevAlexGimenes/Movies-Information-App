package com.alex.movie.information.app.data.api

import com.alex.movie.information.app.data.movies.DMoviesInformationResponse
import retrofit2.http.GET

interface MovieInfoRemoteSource {

    @GET("/3/movie/popular?api_key=59c6c4384e0e3d936ea75affc6d7296e&language=pt-BR&page=2")
    suspend fun getPopularMovieList(): DMoviesInformationResponse

    @GET("3/movie/now_playing?api_key=59c6c4384e0e3d936ea75affc6d7296e&language=pt-BR&page=1")
    suspend fun getNowPlayingMovieList(): DMoviesInformationResponse

    @GET("3/movie/top_rated?api_key=59c6c4384e0e3d936ea75affc6d7296e&language=pt-BR&page=1")
    suspend fun getTopRatedMovies(): DMoviesInformationResponse
}