package com.ryadamir.movieapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.ui.adapters.MovieAdapter
import com.ryadamir.movieapp.listener.OnClickItemMovie
import com.ryadamir.movieapp.model.discover.Discover
import com.ryadamir.movieapp.model.trending.Movie
import com.ryadamir.movieapp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private val adapterTrending: MovieAdapter by lazy {
        MovieAdapter()
    }
    private val adapterTopRated: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListTrending()
        observeTrending()
        setListTopRated()
        observeTopRated()
        observeDiscover()

        viewModel.requestDiscover()
        viewModel.requestTrending()
        viewModel.requestTopRated()

    }

    private fun observeDiscover() {
        viewModel.discoverResponseList.observe(viewLifecycleOwner) {
            loadImageList(it)
            navigationToDetailDiscover(it)
        }
    }

    private fun observeTrending() {
        viewModel.trendingResponseList.observe(viewLifecycleOwner) {
            adapterTrending.setData(it)
            progress.visibility = View.GONE
            trending_title.visibility = View.VISIBLE
        }
    }

    private fun observeTopRated() {
        viewModel.topratedResponseList.observe(viewLifecycleOwner) {
            adapterTopRated.setData(it)
            progress.visibility = View.GONE
            toprated_title.visibility = View.VISIBLE
        }
    }

    private fun loadImageList(data: ArrayList<Discover>) {

        val imageList = ArrayList<SlideModel>() // Create image list

        for (i in 0..5) {
            imageList.add(SlideModel("${BuildConfig.TMDB_ORIGINAL_IMAGE_URL}${data[i].backdropPath}"))
        }

        image_slider.setImageList(imageList, ScaleTypes.CENTER_CROP)
        image_slider.setItemChangeListener(object : ItemChangeListener {
            override fun onItemChanged(position: Int) {
                txt_title_discover?.text = data[position].title
            }
        })
    }

    private fun navigationToDetailDiscover(discover: ArrayList<Discover>) {
        image_slider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("id", discover[position].id)
                startActivity(intent)
            }
        })
    }

    private fun setListTrending() {
        rv_trending.setHasFixedSize(true)
        rv_trending.adapter = adapterTrending
        adapterTrending.onClickListener = object : OnClickItemMovie {
            override fun onClick(movie: Movie) {
                navigationToDetail(movie)
            }
        }
    }

    private fun setListTopRated() {
        rv_toprated.setHasFixedSize(true)
        rv_toprated.adapter = adapterTopRated
        adapterTopRated.onClickListener = object : OnClickItemMovie {
            override fun onClick(movie: Movie) {
                navigationToDetail(movie)
            }
        }
    }

    private fun navigationToDetail(movie: Movie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id", movie.id)
        startActivity(intent)
    }

}