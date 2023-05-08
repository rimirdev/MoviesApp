package com.ryadamir.movieapp.listener

import com.ryadamir.movieapp.model.trending.movies.Movie

interface OnClickItemMovie {
    fun onClick(movie: Movie)
}