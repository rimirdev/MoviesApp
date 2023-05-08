package com.ryadamir.movieapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemFavorite
import com.ryadamir.movieapp.model.favorite.FavoriteEntity
import com.ryadamir.movieapp.ui.adapters.FavoriteAdapter
import com.ryadamir.movieapp.ui.detail.DetailMovieActivity
import com.ryadamir.movieapp.ui.detail.DetailSerieActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by viewModel()

    private val adapterFavorite: FavoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListFavorite()
        observeFavoriteList()
        viewModel.loadFovoriteData()

    }

    private fun observeFavoriteList() {
        viewModel.favoriteEntityList.observe(viewLifecycleOwner) {
            adapterFavorite.setDataFavorite(it)
        }
    }

    private fun setListFavorite() {
        rv_favorite.setHasFixedSize(true)
        rv_favorite.adapter = adapterFavorite
        adapterFavorite.onClickItemFavorite = object : OnClickItemFavorite {
            override fun onClick(favoriteEntity: FavoriteEntity) {
                if (favoriteEntity.type == "movie") {
                    val intent = Intent(activity, DetailMovieActivity::class.java)
                    intent.putExtra("id", favoriteEntity.id)
                    startActivity(intent)
                } else {
                    val intent = Intent(activity, DetailSerieActivity::class.java)
                    intent.putExtra("id", favoriteEntity.id)
                    startActivity(intent)
                }

            }

            override fun onClickFav(favoriteEntity: FavoriteEntity) {
                viewModel.removeMovie(favoriteEntity.id)
                observeFavoriteList()
            }
        }
    }


}