package com.ryadamir.movieapp.listener

import com.ryadamir.movieapp.model.search.movie.SearchMovie

interface OnClickItemSearch {
    fun onClick(searchMovie: SearchMovie)
}