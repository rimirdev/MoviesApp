package com.ryadamir.movieapp.ui.adapters.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemSaison
import com.ryadamir.movieapp.model.episodes.Episode
import kotlinx.android.synthetic.main.list_episodes.view.duration_episode
import kotlinx.android.synthetic.main.list_episodes.view.episode_number
import kotlinx.android.synthetic.main.list_episodes.view.rating
import kotlinx.android.synthetic.main.list_movie.view.*
import kotlinx.android.synthetic.main.list_season.view.title_episodes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.TrendingViewHolder>() {

    private var list: MutableList<Episode> = mutableListOf()
    var onClickListener: OnClickItemSaison? = null

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(episode: Episode) {

            /*itemView.setOnClickListener {
                onClickListener?.onClick(episode)
            }*/

            itemView.title_movie.text = episode.name
            itemView.episode_number.text =
                itemView.context.getString(R.string.episode) + " " + episode.episodeNumber.toString()

            itemView.duration_episode.text =
                episode.runtime.toString() + " " + itemView.context.getString(R.string.mins)

            itemView.rating.text = String.format("%.1f", episode.voteAverage)
                .toDouble().toString()

            Glide.with(itemView).load("${BuildConfig.TMDB_500_IMAGE_URL}${episode.stillPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_movie)
        }
    }

    override fun getItemId(position: Int): Long {
        return list[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_episodes, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: List<Episode>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}