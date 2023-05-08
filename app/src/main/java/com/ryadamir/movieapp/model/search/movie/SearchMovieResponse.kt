package com.ryadamir.movieapp.model.search.movie

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("results")
    val results: ArrayList<SearchMovie>
)
