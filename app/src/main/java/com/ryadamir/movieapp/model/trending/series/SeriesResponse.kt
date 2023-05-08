package com.ryadamir.movieapp.model.trending.series

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("results")
    val results : ArrayList<Series>

)
