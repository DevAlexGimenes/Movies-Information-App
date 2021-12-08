package com.alex.movie.information.app.di

import com.alex.movie.information.app.BuildConfig
import com.alex.movie.information.app.data.api.MovieInfoRemoteSource
import com.alex.movie.information.app.data.api.ServiceFactory
import com.alex.movie.information.app.data.movies.MoviesInfoRepositoryImpl
import com.alex.movie.information.app.domain.movies.repository.MoviesInfoRepository
import com.alex.movie.information.app.domain.movies.usecase.MoviesInfoUseCase
import com.alex.movie.information.app.domain.movies.usecase.MoviesInfoUseCaseImpl
import com.alex.movie.information.app.presentation.movies.top.rated.TopRatedMoviesViewModel
import com.alex.movie.information.app.presentation.movies.now.playing.NowPlayingMoviesViewModel
import com.alex.movie.information.app.presentation.movies.popular.PopularMoviesViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_RETROFIT = "retrofit"
const val KOIN_OKHTTP = "okhttp"

val mainModule = module {

    viewModel {
        PopularMoviesViewModel(get())
    }

    viewModel {
        NowPlayingMoviesViewModel(get())
    }

    viewModel {
        TopRatedMoviesViewModel(get())
    }

    single<MoviesInfoUseCase> {
        MoviesInfoUseCaseImpl(get())
    }

    single<MoviesInfoRepository> {
        MoviesInfoRepositoryImpl(get())
    }

    single {
        Gson()
    }

    single {
        GsonConverterFactory.create(get<Gson>())
    }

    single {
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
    }

    single(named(KOIN_OKHTTP)) {
        OkHttpClient().newBuilder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single(named(KOIN_RETROFIT)) {
        Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get(named(KOIN_OKHTTP)))
            .build()
    }

    single {
        ServiceFactory.createService(get(named(KOIN_RETROFIT)), MovieInfoRemoteSource::class.java)
    }
}