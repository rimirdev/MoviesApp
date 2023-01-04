package com.ryadamir.movieapp.model.trending

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results : ArrayList<Movie>

)
