package com.ryadamir.movieapp.model.episodes

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("id")
    val id: Int,
    @SerializedName("episode_number")
    val episodeNumber: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("still_path")
    val stillPath: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
)
