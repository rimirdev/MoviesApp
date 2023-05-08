package com.ryadamir.movieapp.model.search.serie

import com.google.gson.annotations.SerializedName

data class SearchSerie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
)
