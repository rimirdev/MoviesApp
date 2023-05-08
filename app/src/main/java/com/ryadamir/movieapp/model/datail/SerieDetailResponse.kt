package com.ryadamir.movieapp.model.datail

import com.google.gson.annotations.SerializedName

data class SerieDetailResponse(
    @SerializedName("homepage")
    val url: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("vote_count")
    val ratingCount: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genres")
    val genres: List<GenresDetail>,
    @SerializedName("seasons")
    val seasons: List<Saisons>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val originalTitle: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("first_air_date")
    val releaseDate: String,
    @SerializedName("episode_run_time")
    val duration: List<Int>,
    @SerializedName("number_of_seasons")
    val saisons: Int,
    @SerializedName("number_of_episodes")
    val episodes: Int,
    @SerializedName("status")
    val status: String
)
