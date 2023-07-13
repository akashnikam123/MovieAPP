package com.example.movielistapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.movielistapp.R
import com.bumptech.glide.Glide
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.example.movielistapp.model.Movie
import java.util.ArrayList

class movie_display_activity : AppCompatActivity() {
    var movie_toolbar: Toolbar? = null
    var movie_poster: ImageView? = null
    var movie_title: TextView? = null
    var movie_year: TextView? = null
    var movie_rating: TextView? = null
    var movie_summary: TextView? = null
    var movie_writer: TextView? = null
    var movie_director: TextView? = null
    var movie_cast: TextView? = null
    var arrayList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_display)
        movie_toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(movie_toolbar)

        val b = intent.extras
        val imdbRating = b!!.getString("movieRating")
        val title = b.getString("movieTitle")
        val writer = b.getString("movieWriter")
        val director = b.getString("movieDirector")
        val poster = b.getString("moviePoster")
        val summary = b.getString("movieSummary")
        val cast = b.getString("movieCast")
        val year = b.getString("movieYear")

        movie_title = findViewById(R.id.movie_Title)
        movie_poster = findViewById(R.id.movie_poster)
        movie_year = findViewById(R.id.movie_year)
        movie_director = findViewById(R.id.movie_director)
        movie_rating = findViewById(R.id.movie_rating)
        movie_summary = findViewById(R.id.movie_summary)
        movie_writer = findViewById(R.id.movie_writer)
        movie_cast = findViewById(R.id.movie_cast)


        movie_title?.setText(title)

        movie_director?.setText(director)
        val Year = "<b>Year : <b>$year"
        movie_year?.setText(Html.fromHtml(Year))
        val ImDbRating = "<b>IMdb Rating : <b>$imdbRating"
        movie_rating?.setText(Html.fromHtml(ImDbRating))
        movie_cast?.setText(cast)
        movie_summary?.setText(summary)
        movie_writer?.setText(writer)
        movie_toolbar?.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
    }
}