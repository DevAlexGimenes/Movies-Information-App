package com.alex.movie.information.app.presentation.movies.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.movie.information.app.data.movies.DMoviesInformation
import com.alex.movie.information.app.domain.movies.usecase.MoviesInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMoviesViewModel(
    private val moviesInfoUseCase: MoviesInfoUseCase
) : ViewModel() {

    private val moviesInfoLv = MutableLiveData<List<DMoviesInformation>>()
    fun moviesInfo(): MutableLiveData<List<DMoviesInformation>> = moviesInfoLv

    fun getMoviesInfoCoroutines() {
        CoroutineScope(Dispatchers.Main).launch {
            val moviesInformation = withContext(Dispatchers.Default) {
                moviesInfoUseCase.getPopularMovies()
            }
            moviesInfoLv.value = moviesInformation
        }
    }
}