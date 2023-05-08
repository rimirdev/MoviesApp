package com.ryadamir.movieapp.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemSearch
import com.ryadamir.movieapp.listener.OnClickItemSearchSerie
import com.ryadamir.movieapp.model.search.movie.SearchMovie
import com.ryadamir.movieapp.model.search.serie.SearchSerie
import com.ryadamir.movieapp.ui.adapters.movie.SearchAdapterMovie
import com.ryadamir.movieapp.ui.adapters.serie.SearchAdapterSerie
import com.ryadamir.movieapp.ui.detail.DetailMovieActivity
import com.ryadamir.movieapp.ui.detail.DetailSerieActivity
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()

    private val adapterSearch: SearchAdapterMovie by lazy {
        SearchAdapterMovie()
    }

    private val adapterSearchSerie: SearchAdapterSerie by lazy {
        SearchAdapterSerie()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListSearch()
        search()
        observeSearch()
        observeSearchSerie()

    }

    private fun search() {

        search_movie.requestFocus()

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        search_movie.postDelayed(Runnable {
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }, 100)

        search_movie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_movie.clearFocus()
                viewModel.requestMovieQuery(query)
                viewModel.requestSerieQuery(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeSearch() {
        viewModel.searchMovieList.observe(viewLifecycleOwner) {
            adapterSearch.setDataSearch(it)
            movies_title.visibility = View.VISIBLE
        }
    }
    private fun observeSearchSerie() {
        viewModel.searchSerieList.observe(viewLifecycleOwner) {
            adapterSearchSerie.setDataSearch(it)
            series_title.visibility = View.VISIBLE
        }
    }

    private fun setListSearch() {

        rv_movie_search.setHasFixedSize(true)
        rv_movie_search.adapter = adapterSearch
        adapterSearch.onClickItemSearch = object : OnClickItemSearch {
            override fun onClick(searchMovie: SearchMovie) {
                navigationToDetail(searchMovie)
            }
        }

        rv_serie_search.setHasFixedSize(true)
        rv_serie_search.adapter = adapterSearchSerie
        adapterSearchSerie.onClickItemSearch = object : OnClickItemSearchSerie {
            override fun onClick(searchMovie: SearchSerie) {
                navigationToSerieDetail(searchMovie)
            }
        }
    }

    private fun navigationToDetail(searchMovie: SearchMovie){
        val intent = Intent(activity, DetailMovieActivity::class.java)
        intent.putExtra("id", searchMovie.id)
        startActivity(intent)
    }

    private fun navigationToSerieDetail(searchMovie: SearchSerie){
        val intent = Intent(activity, DetailSerieActivity::class.java)
        intent.putExtra("id", searchMovie.id)
        startActivity(intent)
    }

}




