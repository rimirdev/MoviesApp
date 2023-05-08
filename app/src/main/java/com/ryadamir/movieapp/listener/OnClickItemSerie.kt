package com.ryadamir.movieapp.listener

import com.ryadamir.movieapp.model.trending.movies.Movie
import com.ryadamir.movieapp.model.trending.series.Series

interface OnClickItemSerie {
    fun onClick(movie: Series)
}