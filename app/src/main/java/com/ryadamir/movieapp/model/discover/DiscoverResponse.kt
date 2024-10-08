package com.ryadamir.movieapp.model.discover

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
    @SerializedName("results")
    val results : ArrayList<Discover>
)
