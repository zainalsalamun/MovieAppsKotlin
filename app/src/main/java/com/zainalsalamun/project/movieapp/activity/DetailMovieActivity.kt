package com.zainalsalamun.project.movieapp.activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.zainalsalamun.project.movieapp.R
import com.zainalsalamun.project.movieapp.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"
    private var movie: Movie? = null
    private var position: Int = 0
    private var isFav = false

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val REQUEST_ADD = 110
        const val RESULT_ADD = 111
        const val REQUEST_UPDATE = 210
        const val RESULT_UPDATE = 211
        const val RESULT_DELETE = 311
        const val EXTRA_MOVIE_FAV = "extra_movie_fav"
        const val EXTRA_POSITION_MOVIE = "extra_position_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        //show(movie!!)

    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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


    private fun show(movie: Movie) {
        tvm_title.text = movie.title
        tvm_rating.text = movie.rating.toString()
        tvm_overview.text = movie.overview
        tvm_release.text = movie.releaseDate
        Picasso.get().load(pathPoster + movie.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop + movie.backdropPath).into(ivt_backdrop)
    }
}