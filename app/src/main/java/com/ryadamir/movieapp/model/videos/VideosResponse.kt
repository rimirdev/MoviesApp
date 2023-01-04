package com.ryadamir.movieapp.model.videos

import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("results")
    val results : ArrayList<Videos>
)
