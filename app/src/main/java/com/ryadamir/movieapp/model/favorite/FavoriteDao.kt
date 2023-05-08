package com.ryadamir.movieapp.model.favorite

import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * From favorite")
    fun getAll():List<FavoriteEntity>

    @Insert
    fun insert(favoriteEntity: FavoriteEntity)

    @Delete
    fun delete(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id = :id")
    fun deleteById(id: Int)

    @Update
    fun update(favoriteEntity: FavoriteEntity)

    @Query("SELECT * From favorite Where id = :id")
    fun getMovieById(id:Int): List<FavoriteEntity>
}