package com.ryadamir.movieapp.ui.main

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ryadamir.movieapp.R
import com.ryadamir.movieapp.ui.favorite.FavoriteFragment
import com.ryadamir.movieapp.ui.settings.SettingsFragment
import com.ryadamir.movieapp.ui.home.HomeFragment
import com.ryadamir.movieapp.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFullScreen()
        setBottomBar()

        // Setting up home fragment
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, HomeFragment())
            .commit()
    }

    private fun setBottomBar() {
        bottom_bar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                if (newIndex == 0) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, HomeFragment())
                        .commit()
                } else if (newIndex == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, SearchFragment())
                        .commit()
                } else if (newIndex == 2) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, FavoriteFragment())
                        .commit()
                } else if (newIndex == 3) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, SettingsFragment())
                        .commit()
                }
            }

        })

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