package com.ryadamir.movieapp.ui.adapters.serie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ryadamir.movieapp.model.search.movie.SearchMovie
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemSearch
import com.ryadamir.movieapp.listener.OnClickItemSearchSerie
import com.ryadamir.movieapp.model.search.serie.SearchSerie
import kotlinx.android.synthetic.main.list_search.view.*

class SearchAdapterSerie : RecyclerView.Adapter<SearchAdapterSerie.SearchViewHolder>() {

    private var list: MutableList<SearchSerie> = mutableListOf()
    var onClickItemSearch: OnClickItemSearchSerie?= null


    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(searchMovie: SearchSerie){

            itemView.setOnClickListener {
                onClickItemSearch?.onClick(searchMovie)
            }
            Glide.with(itemView)
                .load("${BuildConfig.TMDB_500_IMAGE_URL}${searchMovie.backdropPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_movie_search)

            itemView.title_movie_search.text = searchMovie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataSearch(data: List<SearchSerie>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}