package com.ryadamir.movieapp.listener

import com.ryadamir.movieapp.model.datail.Saisons
import com.ryadamir.movieapp.model.trending.movies.Movie
import com.ryadamir.movieapp.model.trending.series.Series

interface OnClickItemSaison {
    fun onClick(saisons: Saisons)
}