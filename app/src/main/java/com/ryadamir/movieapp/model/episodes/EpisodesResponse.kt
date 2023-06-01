package com.ryadamir.movieapp.model.episodes

import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName("episodes")
    val episodes : ArrayList<Episode>

)
