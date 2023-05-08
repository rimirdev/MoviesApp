package com.ryadamir.movieapp.model.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name= "vote_average")
    val rating: Float,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,
    @ColumnInfo(name = "posterPath")
    val posterPath: String,
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,
    @ColumnInfo(name = "type")
    val type: String
)





