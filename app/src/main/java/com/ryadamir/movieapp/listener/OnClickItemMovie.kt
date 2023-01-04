package com.ryadamir.movieapp.listener

import com.ryadamir.movieapp.model.trending.Movie

interface OnClickItemMovie {
    fun onClick(movie: Movie)
}