package com.ryadamir.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemMovie
import com.ryadamir.movieapp.model.trending.Movie
import kotlinx.android.synthetic.main.list_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.TrendingViewHolder>() {

    private var list: MutableList<Movie> = mutableListOf()
    var onClickListener: OnClickItemMovie? = null

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {

            itemView.setOnClickListener {
                onClickListener?.onClick(movie)
            }

            itemView.title_movie.text = movie.title

            Glide.with(itemView).load("${BuildConfig.TMDB_IMAGE_URL}${movie.posterPath}")
                .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()
                .into(itemView.iv_trending)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: List<Movie>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}