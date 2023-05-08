package com.ryadamir.movieapp.model.datail

import com.google.gson.annotations.SerializedName

class Saisons(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("episode_count")
    val episodes: Int,
    @SerializedName("season_number")
    val number: Int,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("air_date")
    val date: String
)