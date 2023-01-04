package com.ryadamir.movieapp.data

import com.ryadamir.movieapp.model.cast.CastResponse
import com.ryadamir.movieapp.model.datail.DetailResponse
import com.ryadamir.movieapp.model.videos.VideosResponse
import com.ryadamir.movieapp.model.discover.DiscoverResponse
import com.ryadamir.movieapp.model.trending.MovieResponse
import com.ryadamir.movieapp.remote.Api
import io.reactivex.Observable

class Repository(private val api: Api) {

    fun getDetailMovie(id: Int): Observable<DetailResponse> {
        return api.getDetailMovie(id)
    }

    fun getMovieVideos(id: Int): Observable<VideosResponse> {
        return api.getMovieVideos(id)
    }

    fun getCast(id: Int): Observable<CastResponse> {
        return api.getCast(id)
    }

    fun getDiscover(): Observable<DiscoverResponse> {
        return api.getDiscover()
    }

    fun getTrending(): Observable<MovieResponse> {
        return api.getTrending()
    }

    fun getTopRated(): Observable<MovieResponse> {
        return api.getTopRated()
    }

}