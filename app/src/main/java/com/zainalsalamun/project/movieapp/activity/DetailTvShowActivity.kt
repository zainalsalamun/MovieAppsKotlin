package com.zainalsalamun.project.movieapp.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.zainalsalamun.project.movieapp.R
import com.zainalsalamun.project.movieapp.model.TvShow
import kotlinx.android.synthetic.main.activity_detail_tvshow.*

class DetailTvShowActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"
    private var tvShow: TvShow? = null
    private var position: Int = 0
    private var isFav = false

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_POSITION_TV = "extra_position_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)

        //favoriteTVHelper = FavoriteTVHelper.getInstance(applicationContext)

        tvShow = intent.getParcelableExtra(EXTRA_TVSHOW)

        tvShow = intent.getParcelableExtra(EXTRA_TV)
        if (tvShow != null) {
            position = intent.getIntExtra(EXTRA_POSITION_TV, 0)
            //isFav = true
        } else {
           // tvShow = FavoriteTVShowDB()
        }

        if (isFav) {

        } else {

        }

        setup(tvShow!!)

    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite_menu, menu)
        return true
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->{
                finish()
                return true
            }
            else -> return true
        }
    }


    private fun setup(tvShow: TvShow) {
        tvt_title.text = tvShow.title
        tvm_overview.text = tvShow.overview
        tvm_rating.text = tvShow.rating.toString()
        tvt_release.text = tvShow.firstAirDate
        Picasso.get().load(pathPoster + tvShow.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop + tvShow.backdropPath).into(ivt_backdrop)
    }
}