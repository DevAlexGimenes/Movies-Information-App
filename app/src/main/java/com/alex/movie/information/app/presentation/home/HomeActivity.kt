package com.alex.movie.information.app.presentation.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.alex.movie.information.app.R
import com.alex.movie.information.app.presentation.home.slider.PSliderItem
import com.alex.movie.information.app.presentation.home.slider.SliderAdapter
import com.alex.movie.information.app.presentation.movies.now.playing.NowPlayingMoviesActivity
import com.alex.movie.information.app.presentation.movies.popular.PopularMoviesActivity
import com.alex.movie.information.app.presentation.movies.top.rated.TopRatedMoviesActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.abs

class HomeActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    private val sliderHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()
        configViewPager2()
        compositePageTransformer()
        onViewPagerChangeCallBack()

        btnPopularMovies.setOnClickListener {
            val intent = Intent(this, PopularMoviesActivity::class.java)
            startActivity(intent)
        }

        btnNowPlaying.setOnClickListener {
            val intent = Intent(this, NowPlayingMoviesActivity::class.java)
            startActivity(intent)
        }

        btnTopRated.setOnClickListener {
            val intent = Intent(this, TopRatedMoviesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configViewPager2() {
        val pSliderItems: MutableList<PSliderItem> = ArrayList()

        pSliderItems.add(PSliderItem(R.drawable.poster_path_spider_man))
        pSliderItems.add(PSliderItem(R.drawable.poster_path_avengers_end_game))
        pSliderItems.add(PSliderItem(R.drawable.poster_path_captain_marvel))
        pSliderItems.add(PSliderItem(R.drawable.poster_path_joker))
        pSliderItems.add(PSliderItem(R.drawable.poster_path_star_wars))
        pSliderItems.add(PSliderItem(R.drawable.poster_path_venom))

        viewPager2 = viewPager_ImageSlider
        viewPager2.adapter = SliderAdapter(pSliderItems, viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun compositePageTransformer() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }

        viewPager2.setPageTransformer(compositePageTransformer)
    }

    private fun onViewPagerChangeCallBack() {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}