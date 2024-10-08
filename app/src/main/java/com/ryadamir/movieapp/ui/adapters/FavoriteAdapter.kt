package com.ryadamir.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.listener.OnClickItemFavorite
import com.ryadamir.movieapp.model.favorite.FavoriteEntity
import kotlinx.android.synthetic.main.list_favorite.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var list: MutableList<FavoriteEntity> = mutableListOf()
    var onClickItemFavorite: OnClickItemFavorite? = null

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favoriteEntity: FavoriteEntity) {

            itemView.setOnClickListener {
                onClickItemFavorite?.onClick(favoriteEntity)
            }

            itemView.cb_fav_list.isChecked = true

            itemView.cb_fav_list.setOnClickListener {
                onClickItemFavorite?.onClickFav(favoriteEntity)
            }


            Glide.with(itemView)
                .load("${BuildConfig.TMDB_500_IMAGE_URL}${favoriteEntity.posterPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_detail_fav)
            itemView.txt_title_favorite.text = favoriteEntity.originalTitle
            itemView.txt_year_favorite.text = favoriteEntity.releaseDate

            val rating = favoriteEntity.rating
            itemView.rating_bar.numStars = 5
            itemView.rating_bar.stepSize = 0.5f
            itemView.rating_bar.rating = rating / 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataFavorite(data: List<FavoriteEntity>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}