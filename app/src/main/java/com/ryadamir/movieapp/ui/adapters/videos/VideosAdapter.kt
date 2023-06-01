package com.ryadamir.movieapp.ui.adapters.videos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemMovie
import com.ryadamir.movieapp.model.trending.movies.Movie
import com.ryadamir.movieapp.model.videos.Videos
import kotlinx.android.synthetic.main.list_videos.view.*

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    private var list: MutableList<Videos> = mutableListOf()

    inner class VideosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Videos) {
            itemView.youtube_video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    try {
                        youTubePlayer.cueVideo(video.key, 0f)

                    } catch (e: java.lang.Exception) {
                        itemView.youtube_video.visibility = View.INVISIBLE
                    }
                }
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_videos, parent, false)
        return VideosViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: List<Videos>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}