package com.ryadamir.movieapp.ui.episodes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryadamir.movieapp.databinding.ActivityEpisodesBinding
import com.ryadamir.movieapp.model.datail.MovieDetailResponse
import com.ryadamir.movieapp.ui.adapters.episodes.EpisodesAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class EpisodesActivity : AppCompatActivity() {

    private val binding: ActivityEpisodesBinding by lazy {
        ActivityEpisodesBinding.inflate(layoutInflater)
    }
    private val viewModel: EpisodesViewModel by viewModel()

    private val id: Int by lazy {
        intent.getIntExtra("id", 0)
    }

    private val season: Int by lazy {
        intent.getIntExtra("season", 0)
    }

    private val adapterEpisodes: EpisodesAdapter by lazy {
        EpisodesAdapter()
    }

    private var isReversed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFullScreen()
        navigationBack()

        setListEpisodes()
        observeEpisodes()

        viewModel.requestEpisodes(id, season)
    }

    private fun setFilter() {
        binding.ivFilter.visibility = View.VISIBLE
        binding.ivFilter.setOnClickListener {
            if (isReversed) {
                isReversed = false
                val lm = LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                binding.rvVideos.layoutManager = lm

            } else {
                isReversed = true
                val lm = LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    true
                )
                binding.rvVideos.layoutManager = lm
            }
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


    private fun observeEpisodes() {
        viewModel.episodeResponse.observe(this) {
            adapterEpisodes.setData(it)
            binding.progress.visibility = View.GONE
            binding.trailerTitle.visibility = View.VISIBLE
            binding.rvVideos.visibility = View.VISIBLE
            if (intent.getStringExtra("title")!!.isNotEmpty()) {
                binding.txtTitleDetail.text = intent.getStringExtra("title")
            } else {
                binding.txtTitleDetail.visibility = View.INVISIBLE
            }
        }

        setFilter()

    }

    private fun setListEpisodes() {
        binding.rvVideos.setHasFixedSize(true)
        binding.rvVideos.adapter = adapterEpisodes

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

}