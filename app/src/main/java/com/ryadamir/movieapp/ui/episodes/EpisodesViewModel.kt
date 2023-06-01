package com.ryadamir.movieapp.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.episodes.Episode
import io.reactivex.disposables.CompositeDisposable

class EpisodesViewModel(private val repository: Repository) : ViewModel() {

    private val _episodeResponse = MutableLiveData<ArrayList<Episode>>()
    val episodeResponse: LiveData<ArrayList<Episode>> = _episodeResponse

    private val _errorMessage = MutableLiveData<String>()

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }


    fun requestEpisodes(id: Int, season: Int) {
        val videosResponseMovieDisposable = repository.getEpisodes(id, season)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _episodeResponse.postValue(it.episodes) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(videosResponseMovieDisposable)
    }

}