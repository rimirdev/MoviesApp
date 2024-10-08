package com.ryadamir.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.model.cast.Cast
import kotlinx.android.synthetic.main.list_cast.view.*

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var list: MutableList<Cast> = mutableListOf()

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cast: Cast) {

            Glide.with(itemView).load("${BuildConfig.TMDB_500_IMAGE_URL}${cast.profil}")
                .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()
                .apply(RequestOptions.circleCropTransform()).into(itemView.iv_cast)
            itemView.txt_name_cast.text = cast.originalName
            itemView.txt_name_cast_movie.text = cast.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataCast(data: List<Cast>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}