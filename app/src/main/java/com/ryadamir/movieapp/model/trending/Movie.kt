package com.ryadamir.movieapp.model.trending

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id : Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_title")
    val title: String
)
