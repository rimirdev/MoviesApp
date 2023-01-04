package com.ryadamir.movieapp.remote

import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.util.Constant
import com.ryadamir.movieapp.model.cast.CastResponse
import com.ryadamir.movieapp.model.datail.DetailResponse
import com.ryadamir.movieapp.model.videos.VideosResponse
import com.ryadamir.movieapp.model.discover.DiscoverResponse
import com.ryadamir.movieapp.model.trending.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    fun getTrending(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<DetailResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movie: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<VideosResponse>

    @GET("discover/movie")
    fun getDiscover(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<DiscoverResponse>

    @GET("movie/{movie_id}/credits")
    fun getCast(
        @Path("movie_id") cast: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<CastResponse>

}
