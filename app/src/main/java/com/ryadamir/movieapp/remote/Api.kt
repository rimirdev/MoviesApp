package com.ryadamir.movieapp.remote

import com.ryadamir.movieapp.model.search.movie.SearchMovieResponse
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.util.Constant
import com.ryadamir.movieapp.model.cast.CastResponse
import com.ryadamir.movieapp.model.datail.MovieDetailResponse
import com.ryadamir.movieapp.model.datail.SerieDetailResponse
import com.ryadamir.movieapp.model.videos.VideosResponse
import com.ryadamir.movieapp.model.discover.DiscoverResponse
import com.ryadamir.movieapp.model.episodes.EpisodesResponse
import com.ryadamir.movieapp.model.search.serie.SearchSerieResponse
import com.ryadamir.movieapp.model.trending.movies.MovieResponse
import com.ryadamir.movieapp.model.trending.series.SeriesResponse
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

    @GET("trending/tv/week")
    fun getTrendingSeries(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<SeriesResponse>

    @GET("discover/tv")
    fun getNetflix(
        @Query("with_networks") network: String = "213",
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<SeriesResponse>
    @GET("discover/tv")
    fun getHBO(
        @Query("with_networks") network: String = "49",
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<SeriesResponse>
    @GET("discover/tv")
    fun getApple(
        @Query("with_networks") network: String = "2552",
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<SeriesResponse>
    @GET("discover/tv")
    fun getPrime(
        @Query("with_networks") network: String = "1024",
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<SeriesResponse>
    @GET("discover/tv")
    fun getParamount(
        @Query("with_networks") network: String = "4330",
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<SeriesResponse>

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
    ): Observable<MovieDetailResponse>

    @GET("tv/{tv_id}")
    fun getDetailSerie(
        @Path("tv_id") movie: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<SerieDetailResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movie: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<VideosResponse>

    @GET("tv/{series_id}/season/{season_number}")
    fun getEpisodes(
        @Path("series_id") serie: Int,
        @Path("season_number") season: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<EpisodesResponse>

    @GET("tv/{tv_id}/videos")
    fun getSerieVideos(
        @Path("tv_id") movie: Int,
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

    @GET("tv/{tv_id}/credits")
    fun getSerieCast(
        @Path("tv_id") cast: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<CastResponse>

    @GET("/movie/{movie_id}/recommendations")
    fun getRelated(
        @Path("movie_id") cast: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Observable<MovieResponse>

    @GET("search/movie")
    fun getSearchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE,
        @Query("include_adult") adult: String = Constant.INCLUDE_ADULT
    ): Observable<SearchMovieResponse>

    @GET("search/tv")
    fun getSearchSerie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE,
        @Query("include_adult") adult: String = Constant.INCLUDE_ADULT
    ): Observable<SearchSerieResponse>
}
