package com.ryadamir.movieapp.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.databinding.ActivityDetailBinding
import com.ryadamir.movieapp.model.datail.MovieDetailResponse
import com.ryadamir.movieapp.model.videos.Videos
import com.ryadamir.movieapp.ui.adapters.CastAdapter
import com.ryadamir.movieapp.ui.adapters.movie.MovieAdapter
import com.ryadamir.movieapp.util.convertDuration
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailMovieActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailViewModel by viewModel()

    private val adapterCast: CastAdapter by lazy {
        CastAdapter()
    }
    private val id: Int by lazy {
        intent.getIntExtra("id", 0)
    }

    private val adapterMovie: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFullScreen()
        navigationBack()
        setListCast()
        observeCast()
        observeDetailMovie()
        observeVideoMovie()
        observeIsFavorited()

        setListRelated()
        observeRelated()

        viewModel.requestCast(id)
        viewModel.requestRelated(id)
        viewModel.requestDetailMovie(id)
        viewModel.requestMovieVideos(id)

        playCloudStream()
    }

    private fun playCloudStream() {
        play_cloudstream.setOnClickListener {


            val intent = Intent(Intent.ACTION_MAIN)
            intent.action = "com.lagradost.cloudstream3.SEARCH"
            val bundle = Bundle()
            bundle.putString("title", "avatar")
            intent.putExtra("search", bundle)
            intent.type = "text/plain"
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(
                    applicationContext,
                    " launch Intent not available",
                    Toast.LENGTH_SHORT
                ).show()
            }
            startActivity(intent)

        }
    }

    private fun shareMovie(detailResponse: MovieDetailResponse) {
        binding.btnShare.setOnClickListener {
            val url = detailResponse.url
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun observeDetailMovie() {
        viewModel.detailResponse.observe(this) {
            binding.progress.visibility = View.GONE
            loadPoster(it)
            loadDetail(it)
            shareMovie(it)
            viewModel.checkFavMovie()
            binding.infosContainer.visibility = View.VISIBLE
            binding.playCloudstream.visibility = View.GONE
        }
    }

    private fun observeVideoMovie() {
        viewModel.videosResponse.observe(this) {
            loadVideo(it)
            binding.trailerTitle.visibility = View.VISIBLE
            binding.youtubeVideo.visibility = View.VISIBLE
        }
    }

    private fun observeCast() {
        viewModel.castResponseList.observe(this) {
            adapterCast.setDataCast(it)
            binding.castTitle.visibility = View.VISIBLE
            binding.rvCast.visibility = View.VISIBLE

        }
    }

    private fun observeRelated() {
        viewModel.relatedResponseList.observe(this) {
            adapterMovie.setData(it)
            binding.relatedTitle.visibility = View.VISIBLE
            binding.rvRelated.visibility = View.VISIBLE

        }
    }

    private fun observeIsFavorited() {
        viewModel.isFavorited.observe(this) {
            cb_fav.isChecked = it
            addFavoriteMovie()
        }
    }

    private fun loadPoster(detailResponse: MovieDetailResponse) {
        Glide.with(this)
            .load("${BuildConfig.TMDB_ORIGINAL_IMAGE_URL}${detailResponse.backdropPath}")
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.ivPoster)
    }

    @SuppressLint("SetTextI18n")
    private fun loadDetail(detailResponse: MovieDetailResponse) {
        binding.txtTitleDetail.text = detailResponse.originalTitle
        binding.txtReleaseDetail.text =
            resources.getString(R.string.released_on) + detailResponse.releaseDate
        binding.txtGenreDetail.text = detailResponse.genres.joinToString(" , ") { it.nameGenre }
        binding.txtDescriptionDetail.text = detailResponse.description
        binding.txtDurationDetail.text =
            resources.getString(R.string.duration) + convertDuration(detailResponse.duration)
        binding.txtRatingDetail.text =
            resources.getString(R.string.rating) + String.format("%.1f", detailResponse.rating)
                .toDouble() + " /10"
        binding.ratingBar.rating = detailResponse.rating / 2

    }

    private fun loadVideo(videos: ArrayList<Videos>) {
        binding.youtubeVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                try {
                    youTubePlayer.cueVideo(videos[0].key, 0f)

                } catch (e: java.lang.Exception) {
                    binding.youtubeVideo.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun setListCast() {
        binding.rvCast.setHasFixedSize(true)
        binding.rvCast.adapter = adapterCast
    }

    private fun setListRelated() {
        binding.rvRelated.setHasFixedSize(true)
        binding.rvRelated.adapter = adapterMovie
    }

    private fun navigationBack() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setFullScreen() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }

    private fun addFavoriteMovie() {
        binding.cbFav.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                viewModel.saveMovie()
            } else {
                viewModel.removeMovie()
            }
        }
    }

}