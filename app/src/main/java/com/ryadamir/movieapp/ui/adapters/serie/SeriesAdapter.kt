package com.ryadamir.movieapp.ui.adapters.serie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemSerie
import com.ryadamir.movieapp.model.trending.series.Series
import kotlinx.android.synthetic.main.list_movie.view.*

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.TrendingViewHolder>() {

    private var list: MutableList<Series> = mutableListOf()
    var onClickListener: OnClickItemSerie? = null

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Series) {

            itemView.setOnClickListener {
                onClickListener?.onClick(movie)
            }

            itemView.title_movie.text = movie.title

            Glide.with(itemView).load("${BuildConfig.TMDB_500_IMAGE_URL}${movie.posterPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_movie)

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

    fun setData(data: List<Series>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}