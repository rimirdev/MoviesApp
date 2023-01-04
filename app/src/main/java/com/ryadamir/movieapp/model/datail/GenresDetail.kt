package com.ryadamir.movieapp.model.datail

import com.google.gson.annotations.SerializedName

class GenresDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nameGenre: String
)