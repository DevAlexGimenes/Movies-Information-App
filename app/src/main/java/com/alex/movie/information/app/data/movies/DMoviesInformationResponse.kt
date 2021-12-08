package com.alex.movie.information.app.data.movies

import com.google.gson.annotations.SerializedName

data class DMoviesInformationResponse(
    @SerializedName("results")
    val movies : List<DMoviesInformation>
)
