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
import com.ryadamir.movieapp.model.datail.Saisons
import com.ryadamir.movieapp.model.trending.series.Series
import kotlinx.android.synthetic.main.list_movie.view.*
import kotlinx.android.synthetic.main.list_movie.view.iv_movie
import kotlinx.android.synthetic.main.list_movie.view.title_movie
import kotlinx.android.synthetic.main.list_season.view.*

class SeasonsAdapter : RecyclerView.Adapter<SeasonsAdapter.TrendingViewHolder>() {

    private var list: MutableList<Saisons> = mutableListOf()
    var onClickListener: OnClickItemSerie? = null

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Saisons) {

            /*itemView.setOnClickListener {
                onClickListener?.onClick(movie)
            }*/

            itemView.title_movie.text =
                itemView.context.getString(R.string.saison) + " " + movie.number

            itemView.title_episodes.text =
                movie.episodes.toString() + " " + itemView.context.getString(R.string.episodes2)

            try {
                itemView.title_year.text =
                    movie.date.dropLast(movie.date.length - 4)
            } catch (e: Exception) {

            }

            Glide.with(itemView).load("${BuildConfig.TMDB_500_IMAGE_URL}${movie.poster}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_movie)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_season, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: List<Saisons>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}