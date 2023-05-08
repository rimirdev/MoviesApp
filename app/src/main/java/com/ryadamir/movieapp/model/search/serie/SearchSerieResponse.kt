package com.ryadamir.movieapp.model.search.serie

import com.google.gson.annotations.SerializedName

data class SearchSerieResponse(
    @SerializedName("results")
    val results: ArrayList<SearchSerie>
)
