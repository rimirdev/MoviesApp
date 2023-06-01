package com.ryadamir.movieapp.data

import com.ryadamir.movieapp.model.search.movie.SearchMovieResponse
import com.ryadamir.movieapp.model.cast.CastResponse
import com.ryadamir.movieapp.model.datail.MovieDetailResponse
import com.ryadamir.movieapp.model.datail.SerieDetailResponse
import com.ryadamir.movieapp.model.videos.VideosResponse
import com.ryadamir.movieapp.model.discover.DiscoverResponse
import com.ryadamir.movieapp.model.episodes.EpisodesResponse
import com.ryadamir.movieapp.model.favorite.FavoriteDatabase
import com.ryadamir.movieapp.model.favorite.FavoriteEntity
import com.ryadamir.movieapp.model.search.serie.SearchSerie
import com.ryadamir.movieapp.model.search.serie.SearchSerieResponse
import com.ryadamir.movieapp.model.trending.movies.MovieResponse
import com.ryadamir.movieapp.model.trending.series.SeriesResponse
import com.ryadamir.movieapp.remote.Api
import io.reactivex.Observable

class Repository(private val api: Api, val favoriteDatabase: FavoriteDatabase) {

    fun getDetailMovie(id: Int): Observable<MovieDetailResponse> {
        return api.getDetailMovie(id)
    }
    fun getDetailSerie(id: Int): Observable<SerieDetailResponse> {
        return api.getDetailSerie(id)
    }

    fun getMovieVideos(id: Int): Observable<VideosResponse> {
        return api.getMovieVideos(id)
    }

    fun getEpisodes(id: Int, season: Int): Observable<EpisodesResponse> {
        return api.getEpisodes(id, season)
    }

    fun getSerieVideos(id: Int): Observable<VideosResponse> {
        return api.getSerieVideos(id)
    }

    fun getCast(id: Int): Observable<CastResponse> {
        return api.getCast(id)
    }

    fun getSerieCast(id: Int): Observable<CastResponse> {
        return api.getSerieCast(id)
    }

    fun getRelated(id: Int): Observable<MovieResponse> {
        return api.getRelated(id)
    }

    fun getDiscover(): Observable<DiscoverResponse> {
        return api.getDiscover()
    }

    fun getTrending(): Observable<MovieResponse> {
        return api.getTrending()
    }
    fun getTrendingSeries(): Observable<SeriesResponse> {
        return api.getTrendingSeries()
    }
    fun getNetflix(): Observable<SeriesResponse> {
        return api.getNetflix()
    }

    fun getHBO(): Observable<SeriesResponse> {
        return api.getHBO()
    }
    fun getApple(): Observable<SeriesResponse> {
        return api.getApple()
    }
    fun getPrime(): Observable<SeriesResponse> {
        return api.getPrime()
    }
    fun getParamount(): Observable<SeriesResponse> {
        return api.getParamount()
    }
    fun getTopRated(): Observable<MovieResponse> {
        return api.getTopRated()
    }

    fun getSearchMovie(query: String?): Observable<SearchMovieResponse> {
        return api.getSearchMovie(query.orEmpty())
    }

    fun getSearchSerie(query: String?): Observable<SearchSerieResponse> {
        return api.getSearchSerie(query.orEmpty())
    }
    fun saveMovie(favoriteEntity: FavoriteEntity) {
        favoriteDatabase?.favoriteDao()?.insert(favoriteEntity)
    }

    fun removeMovie(id: Int) {
        favoriteDatabase?.favoriteDao()?.deleteById(id)
    }

    fun checkMovie(id: Int): Boolean {
        val isFavorited = favoriteDatabase?.favoriteDao()?.getMovieById(id)?.size != 0
        return isFavorited
    }

    fun getAllDb(): ArrayList<FavoriteEntity> {
        val dataFromBd = favoriteDatabase?.favoriteDao()?.getAll().orEmpty()
        return dataFromBd as ArrayList<FavoriteEntity>
    }

}