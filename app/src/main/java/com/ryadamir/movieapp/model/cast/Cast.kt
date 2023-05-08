package com.ryadamir.movieapp.model.cast

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id")
    val id: Int,
    @SerializedName("character")
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("profile_path")
    val profil: String
)
