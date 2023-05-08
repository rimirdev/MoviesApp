package com.ryadamir.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.cast.Cast
import com.ryadamir.movieapp.model.datail.MovieDetailResponse
import com.ryadamir.movieapp.model.datail.SerieDetailResponse
import com.ryadamir.movieapp.model.favorite.FavoriteEntity
import com.ryadamir.movieapp.model.trending.movies.Movie
import com.ryadamir.movieapp.model.videos.Videos
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private val _detailResponse = MutableLiveData<MovieDetailResponse>()
    val detailResponse: LiveData<MovieDetailResponse> = _detailResponse

    private val _detailSerieResponse = MutableLiveData<SerieDetailResponse>()
    val detailSerieResponse: LiveData<SerieDetailResponse> = _detailSerieResponse

    private val _videosResponse = MutableLiveData<ArrayList<Videos>>()
    val videosResponse: LiveData<ArrayList<Videos>> = _videosResponse

    private val _castResponseList = MutableLiveData<ArrayList<Cast>>()
    val castResponseList: LiveData<ArrayList<Cast>> = _castResponseList

    private val _relatedResponseList = MutableLiveData<ArrayList<Movie>>()
    val relatedResponseList: LiveData<ArrayList<Movie>> = _relatedResponseList

    private val _isfavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean> = _isfavorited

    private val _errorMessage = MutableLiveData<String>()

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun requestDetailMovie(id: Int) {
        val detailMovieDisposable = repository.getDetailMovie(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _detailResponse.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(detailMovieDisposable)
    }

    fun requestDetailSerie(id: Int) {
        val detailMovieDisposable = repository.getDetailSerie(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _detailSerieResponse.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(detailMovieDisposable)
    }

    fun requestMovieVideos(id: Int) {
        val videosResponseMovieDisposable = repository.getMovieVideos(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _videosResponse.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(videosResponseMovieDisposable)
    }

    fun requestSerieVideos(id: Int) {
        val videosResponseMovieDisposable = repository.getSerieVideos(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _videosResponse.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(videosResponseMovieDisposable)
    }

    fun requestCast(id: Int) {
        val castDisposable = repository.getCast(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _castResponseList.postValue(it.cast) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(castDisposable)
    }

    fun requestSerieCast(id: Int) {
        val castDisposable = repository.getSerieCast(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _castResponseList.postValue(it.cast) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(castDisposable)
    }

    fun requestRelated(id: Int) {
        val castDisposable = repository.getRelated(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _relatedResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(castDisposable)
    }

    fun saveMovie() {
        val detail = _detailResponse.value!!
        val favorite = FavoriteEntity(
            id = detail.id,
            rating = detail.rating,
            backdropPath = detail.backdropPath,
            posterPath = detail.posterPath,
            originalTitle = detail.originalTitle,
            releaseDate = detail.releaseDate,
            type = "movie"
            )
        repository.saveMovie(favorite)
    }

    fun saveSerie() {
        val detail = _detailSerieResponse.value!!
        val favorite = FavoriteEntity(
            id = detail.id,
            rating = detail.rating,
            backdropPath = detail.backdropPath,
            posterPath = detail.posterPath,
            originalTitle = detail.originalTitle,
            releaseDate = detail.releaseDate,
            type = "show"
        )
        repository.saveMovie(favorite)
    }

    fun removeMovie() {
        val detail = _detailResponse.value!!
        repository.removeMovie(detail.id)
    }

    fun removeShow() {
        val detail = _detailSerieResponse.value!!
        repository.removeMovie(detail.id)
    }

    fun checkFavMovie() {
        val detail = _detailResponse.value!!
        _isfavorited.postValue(repository.checkMovie(detail.id))
    }
    fun checkFavSerie() {
        val detail = _detailSerieResponse.value!!
        _isfavorited.postValue(repository.checkMovie(detail.id))
    }
}