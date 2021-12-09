package com.alex.movie.information.app.data.movies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DMoviesInformation(
    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("release_date")
    val release: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("adult")
    val forAdults: Boolean?

) : Parcelable {
    constructor() : this("", "", "", "", "", false)
}