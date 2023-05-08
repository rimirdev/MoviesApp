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
import com.ryadamir.movieapp.databinding.ActivityDetailSerieBinding
import com.ryadamir.movieapp.model.datail.MovieDetailResponse
import com.ryadamir.movieapp.model.datail.SerieDetailResponse
import com.ryadamir.movieapp.model.videos.Videos
import com.ryadamir.movieapp.ui.adapters.CastAdapter
import com.ryadamir.movieapp.ui.adapters.serie.SeasonsAdapter
import com.ryadamir.movieapp.util.convertDuration
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel


class DetailSerieActivity : AppCompatActivity() {

    private val binding: ActivityDetailSerieBinding by lazy {
        ActivityDetailSerieBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailViewModel by viewModel()

    private val adapterCast: CastAdapter by lazy {
        CastAdapter()
    }
    private val adapterSeasons: SeasonsAdapter by lazy {
        SeasonsAdapter()
    }
    private val id: Int by lazy {
        intent.getIntExtra("id", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFullScreen()
        navigationBack()
        setListCast()
        setListSeasons()

        observeCast()
        observeDetailMovie()
        observeVideoMovie()
        observeIsFavorited()

        viewModel.requestSerieCast(id)
        viewModel.requestDetailSerie(id)
        viewModel.requestSerieVideos(id)

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

    private fun shareMovie(detailResponse: SerieDetailResponse) {
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
        viewModel.detailSerieResponse.observe(this) {
            binding.progress.visibility = View.GONE
            loadPoster(it)
            loadDetail(it)
            shareMovie(it)
            viewModel.checkFavSerie()

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

    private fun observeIsFavorited() {
        viewModel.isFavorited.observe(this) {
            cb_fav.isChecked = it
            addFavoriteSerie()
        }
    }

    private fun loadPoster(detailResponse: SerieDetailResponse) {
        Glide.with(this)
            .load("${BuildConfig.TMDB_ORIGINAL_IMAGE_URL}${detailResponse.backdropPath}")
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.ivPoster)
    }

    @SuppressLint("SetTextI18n")
    private fun loadDetail(detailResponse: SerieDetailResponse) {
        binding.txtTitleDetail.text = detailResponse.originalTitle
        binding.txtReleaseDetail.text = resources.getString(R.string.released_on) + detailResponse.releaseDate
        binding.txtStatus.text = resources.getString(R.string.status) + detailResponse.status
        binding.txtGenreDetail.text = detailResponse.genres.joinToString(" , ") { it.nameGenre }
        binding.txtDescriptionDetail.text = detailResponse.description

        if (detailResponse.duration.isNotEmpty())
            binding.txtDurationEpisode.text = resources.getString(R.string.episode_duration) + convertDuration(detailResponse.duration[0])
        else
            binding.txtDurationEpisode.visibility = View.GONE

        binding.txtRatingDetail.text = resources.getString(R.string.rating) + String.format("%.1f", detailResponse.rating).toDouble() + " /10"
        binding.txtRatingCount.text = "(" + detailResponse.ratingCount.toString() + " " + resources.getString(R.string.votes) + ")"
        binding.ratingBar.rating = detailResponse.rating / 2
        binding.txtSaisonsNumber.text =  resources.getString(R.string.saisons) + detailResponse.saisons
        binding.txtEpisodesNumber.text =  resources.getString(R.string.episodes) + detailResponse.episodes

        binding.seasonsTitle.visibility = View.VISIBLE
        binding.rvSeasons.visibility = View.VISIBLE
        adapterSeasons.setData(detailResponse.seasons)
    }

    private fun loadVideo(videos: ArrayList<Videos>) {
        binding.youtubeVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                try {
                    youTubePlayer.cueVideo(videos[0].key, 0f)

                }catch (e : java.lang.Exception) {
                    binding.youtubeVideo.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun setListCast() {
        binding.rvCast.setHasFixedSize(true)
        binding.rvCast.adapter = adapterCast
    }

    private fun setListSeasons() {
        binding.rvSeasons.setHasFixedSize(true)
        binding.rvSeasons.adapter = adapterSeasons
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

    private fun addFavoriteSerie() {
        binding.cbFav.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                viewModel.saveSerie()
            } else {
                viewModel.removeShow()
            }
        }
    }
}