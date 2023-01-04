package com.ryadamir.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.cast.Cast
import com.ryadamir.movieapp.model.datail.DetailResponse
import com.ryadamir.movieapp.model.videos.Videos
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse: LiveData<DetailResponse> = _detailResponse

    private val _videosResponse = MutableLiveData<ArrayList<Videos>>()
    val videosResponse: LiveData<ArrayList<Videos>> = _videosResponse

    private val _castResponseList = MutableLiveData<ArrayList<Cast>>()
    val castResponseList: LiveData<ArrayList<Cast>> = _castResponseList

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

    fun requestMovieVideos(id: Int) {
        val videosResponseMovieDisposable = repository.getMovieVideos(id)
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
}