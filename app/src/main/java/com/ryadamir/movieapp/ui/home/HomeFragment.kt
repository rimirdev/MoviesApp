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
import com.ryadamir.movieapp.ui.adapters.movie.MovieAdapter
import com.ryadamir.movieapp.listener.OnClickItemMovie
import com.ryadamir.movieapp.listener.OnClickItemSerie
import com.ryadamir.movieapp.model.discover.Discover
import com.ryadamir.movieapp.model.trending.movies.Movie
import com.ryadamir.movieapp.model.trending.series.Series
import com.ryadamir.movieapp.ui.adapters.serie.SeriesAdapter
import com.ryadamir.movieapp.ui.detail.DetailMovieActivity
import com.ryadamir.movieapp.ui.detail.DetailSerieActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private val adapterTrending: MovieAdapter by lazy {
        MovieAdapter()
    }
    private val adapterTrendingSeries: SeriesAdapter by lazy {
        SeriesAdapter()
    }
    private val adapterNetflix: SeriesAdapter by lazy {
        SeriesAdapter()
    }

    private val adapterHbo: SeriesAdapter by lazy {
        SeriesAdapter()
    }

    private val adapterApple: SeriesAdapter by lazy {
        SeriesAdapter()
    }
    private val adapterPrime: SeriesAdapter by lazy {
        SeriesAdapter()
    }
    private val adapterParamount: SeriesAdapter by lazy {
        SeriesAdapter()
    }
    private val adapterTopRated: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListTrending()
        setListTrendingSeries()
        setListNetflix()
        setListHBO()
        setListApple()
        setListPrime()
        setListParamount()
        setListTopRated()

        observeDiscover()
        observeTrending()
        observeTrendingSeries()
        observeNetflix()
        observeHBO()
        observeApple()
        observePrime()
        observeParamount()
        observeTopRated()

        viewModel.requestDiscover()
        viewModel.requestTrending()
        viewModel.requestTrendingSeries()
        viewModel.requestNetflix()
        viewModel.requestHBO()
        viewModel.requestApple()
        viewModel.requestPrime()
        viewModel.requestParamount()
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
            trending_movies_title.visibility = View.VISIBLE
        }
    }

    private fun observeNetflix() {
        viewModel.netflixResponseList.observe(viewLifecycleOwner) {
            adapterNetflix.setData(it)
            progress.visibility = View.GONE
            netflix_title.visibility = View.VISIBLE
        }
    }

    private fun observeHBO() {
        viewModel.hboResponseList.observe(viewLifecycleOwner) {
            adapterHbo.setData(it)
            progress.visibility = View.GONE
            hbo_title.visibility = View.VISIBLE
        }
    }

    private fun observeApple() {
        viewModel.appleResponseList.observe(viewLifecycleOwner) {
            adapterApple.setData(it)
            progress.visibility = View.GONE
            apple_title.visibility = View.VISIBLE
        }
    }

    private fun observePrime() {
        viewModel.primeResponseList.observe(viewLifecycleOwner) {
            adapterPrime.setData(it)
            progress.visibility = View.GONE
            prime_title.visibility = View.VISIBLE
        }
    }
    private fun observeParamount() {
        viewModel.paramountResponseList.observe(viewLifecycleOwner) {
            adapterParamount.setData(it)
            progress.visibility = View.GONE
            paramount_title.visibility = View.VISIBLE
        }
    }

    private fun observeTrendingSeries() {
        viewModel.trendingSeriesResponseList.observe(viewLifecycleOwner) {
            adapterTrendingSeries.setData(it)
            progress.visibility = View.GONE
            trending_series_title.visibility = View.VISIBLE
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
                val intent = Intent(activity, DetailMovieActivity::class.java)
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

    private fun setListTrendingSeries() {
        rv_trending_series.setHasFixedSize(true)
        rv_trending_series.adapter = adapterTrendingSeries
        adapterTrendingSeries.onClickListener = object : OnClickItemSerie {
            override fun onClick(movie: Series) {
                navigationToDetailSeries(movie)
            }
        }
    }

    private fun setListNetflix() {
        rv_netflix.setHasFixedSize(true)
        rv_netflix.adapter = adapterNetflix
        adapterNetflix.onClickListener = object : OnClickItemSerie {
            override fun onClick(movie: Series) {
                navigationToDetailSeries(movie)
            }
        }
    }

    private fun setListHBO() {
        rv_hbo.setHasFixedSize(true)
        rv_hbo.adapter = adapterHbo
        adapterHbo.onClickListener = object : OnClickItemSerie {
            override fun onClick(movie: Series) {
                navigationToDetailSeries(movie)
            }
        }
    }

    private fun setListApple() {
        rv_apple.setHasFixedSize(true)
        rv_apple.adapter = adapterApple
        adapterApple.onClickListener = object : OnClickItemSerie {
            override fun onClick(movie: Series) {
                navigationToDetailSeries(movie)
            }
        }
    }

    private fun setListPrime() {
        rv_prime.setHasFixedSize(true)
        rv_prime.adapter = adapterPrime
        adapterPrime.onClickListener = object : OnClickItemSerie {
            override fun onClick(movie: Series) {
                navigationToDetailSeries(movie)
            }
        }
    }

    private fun setListParamount() {
        rv_paramount.setHasFixedSize(true)
        rv_paramount.adapter = adapterParamount
        adapterParamount.onClickListener = object : OnClickItemSerie {
            override fun onClick(movie: Series) {
                navigationToDetailSeries(movie)
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
        val intent = Intent(activity, DetailMovieActivity::class.java)
        intent.putExtra("id", movie.id)
        startActivity(intent)
    }

    private fun navigationToDetailSeries(movie: Series) {
        val intent = Intent(activity, DetailSerieActivity::class.java)
        intent.putExtra("id", movie.id)
        startActivity(intent)
    }

}