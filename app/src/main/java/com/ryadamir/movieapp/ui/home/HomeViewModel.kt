package com.ryadamir.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.discover.Discover
import com.ryadamir.movieapp.model.trending.Movie
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _discoverResponseList = MutableLiveData<ArrayList<Discover>>()
    val discoverResponseList: LiveData<ArrayList<Discover>> = _discoverResponseList

    private val _trendingResponseList = MutableLiveData<ArrayList<Movie>>()
    val trendingResponseList: LiveData<ArrayList<Movie>> = _trendingResponseList

    private val _topratedResponseList = MutableLiveData<ArrayList<Movie>>()
    val topratedResponseList: LiveData<ArrayList<Movie>> = _topratedResponseList

    private val _errorMessage = MutableLiveData<String>()

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun requestDiscover() {
        val discoverDisposable = repository.getDiscover()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe({ _discoverResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(discoverDisposable)
    }

    fun requestTrending() {
        val trendingDisposable = repository.getTrending()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _trendingResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(trendingDisposable)
    }

    fun requestTopRated() {
        val trendingDisposable = repository.getTopRated()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _topratedResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(trendingDisposable)
    }

}