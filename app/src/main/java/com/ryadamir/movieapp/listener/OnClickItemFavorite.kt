package com.ryadamir.movieapp.listener

import com.ryadamir.movieapp.model.favorite.FavoriteEntity


interface OnClickItemFavorite {
    fun onClick(favoriteEntity: FavoriteEntity)
    fun onClickFav(favoriteEntity: FavoriteEntity)
}