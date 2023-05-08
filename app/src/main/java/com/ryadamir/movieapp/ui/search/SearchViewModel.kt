package com.ryadamir.movieapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.search.movie.SearchMovie
import com.ryadamir.movieapp.model.search.serie.SearchSerie

import io.reactivex.disposables.CompositeDisposable


class SearchViewModel(val repository: Repository) : ViewModel() {

    private val _searchMovieList = MutableLiveData<ArrayList<SearchMovie>>()
    val searchMovieList: LiveData<ArrayList<SearchMovie>> = _searchMovieList

    private val _searchSerieList = MutableLiveData<ArrayList<SearchSerie>>()
    val searchSerieList: LiveData<ArrayList<SearchSerie>> = _searchSerieList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun requestMovieQuery(query: String?) {
        val movieQueryDisposable = repository.getSearchMovie(query.orEmpty())
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _searchMovieList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(movieQueryDisposable)
    }

    fun requestSerieQuery(query: String?) {
        val movieQueryDisposable = repository.getSearchSerie(query.orEmpty())
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _searchSerieList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(movieQueryDisposable)
    }
}