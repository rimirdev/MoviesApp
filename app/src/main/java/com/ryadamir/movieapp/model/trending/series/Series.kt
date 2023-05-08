package com.ryadamir.movieapp.model.trending.series

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("id")
    val id : Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("name")
    val title: String
)
