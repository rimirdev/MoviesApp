package com.ryadamir.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.discover.Discover
import com.ryadamir.movieapp.model.trending.movies.Movie
import com.ryadamir.movieapp.model.trending.series.Series
import com.ryadamir.movieapp.model.trending.series.SeriesResponse
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _discoverResponseList = MutableLiveData<ArrayList<Discover>>()
    val discoverResponseList: LiveData<ArrayList<Discover>> = _discoverResponseList

    private val _trendingResponseList = MutableLiveData<ArrayList<Movie>>()
    val trendingResponseList: LiveData<ArrayList<Movie>> = _trendingResponseList

    private val _trendingSeriesResponseList = MutableLiveData<ArrayList<Series>>()
    val trendingSeriesResponseList: LiveData<ArrayList<Series>> = _trendingSeriesResponseList

    private val _netflixResponseList = MutableLiveData<ArrayList<Series>>()
    val netflixResponseList: LiveData<ArrayList<Series>> = _netflixResponseList

    private val _hboResponseList = MutableLiveData<ArrayList<Series>>()
    val hboResponseList: LiveData<ArrayList<Series>> = _hboResponseList

    private val _appleResponseList = MutableLiveData<ArrayList<Series>>()
    val appleResponseList: LiveData<ArrayList<Series>> = _appleResponseList

    private val _primeResponseList = MutableLiveData<ArrayList<Series>>()
    val primeResponseList: LiveData<ArrayList<Series>> = _primeResponseList

    private val _paramountResponseList = MutableLiveData<ArrayList<Series>>()
    val paramountResponseList: LiveData<ArrayList<Series>> = _paramountResponseList

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

    fun requestTrendingSeries() {
        val trendingDisposable = repository.getTrendingSeries()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _trendingSeriesResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(trendingDisposable)
    }

    fun requestNetflix() {
        val trendingDisposable = repository.getNetflix()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _netflixResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(trendingDisposable)
    }
    fun requestHBO() {
        val trendingDisposable = repository.getHBO()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _hboResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(trendingDisposable)
    }

    fun requestApple() {
        val trendingDisposable = repository.getApple()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _appleResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(trendingDisposable)
    }

    fun requestPrime() {
        val trendingDisposable = repository.getPrime()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _primeResponseList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(trendingDisposable)
    }
    fun requestParamount() {
        val trendingDisposable = repository.getParamount()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _paramountResponseList.postValue(it.results) },
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