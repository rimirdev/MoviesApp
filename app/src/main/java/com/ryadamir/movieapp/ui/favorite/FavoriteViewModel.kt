package com.ryadamir.movieapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.favorite.FavoriteEntity

class FavoriteViewModel(val repository: Repository) : ViewModel() {

    private val _favoriteEntityList = MutableLiveData<ArrayList<FavoriteEntity>>()
    val favoriteEntityList: LiveData<ArrayList<FavoriteEntity>> = _favoriteEntityList

    private var dataFavorite: ArrayList<FavoriteEntity> = ArrayList()

    fun loadFovoriteData() {
        dataFavorite.clear()
        dataFavorite.addAll(repository.getAllDb())
        _favoriteEntityList.postValue(dataFavorite)
    }

    fun removeMovie(id:Int) {
        repository.removeMovie(id)
        loadFovoriteData()
    }

}
