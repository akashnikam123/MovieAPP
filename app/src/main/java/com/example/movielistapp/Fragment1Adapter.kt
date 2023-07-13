package com.example.movielistapp


import com.example.movielistapp.Interface.delete_Movie
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.Database.SqliteData
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.movielistapp.R
import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import com.bumptech.glide.Glide
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.example.movielistapp.movie_display_activity
import android.widget.Toast
import android.widget.TextView
import com.example.movielistapp.model.Movie
import java.util.ArrayList

class Fragment1Adapter(arrayList: ArrayList<Movie>, context: Context, delete_movie: delete_Movie) :
    RecyclerView.Adapter<Fragment1Adapter.holder>() {
    var arrayList = ArrayList<Movie>()
    var context: Context
    var sqliteData: SqliteData? = null
    var delete_movie: delete_Movie
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        return holder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment1_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: holder, @SuppressLint("RecyclerView") position: Int) {
        val model = arrayList[position]
        sqliteData = SqliteData(context)
        val Title = "Movie Name  : " + "<b>" + model.title + "</b>"
        holder.movieTitle.text = Html.fromHtml(Title)
        val Year = "Year  : " + "<b>" + model.year + "</b>"
        holder.movieYear.text = Html.fromHtml(Year)
        val Genre = "Genre : " + "<b>" + model.genres + "</b>"
        holder.movieGenre.text = Html.fromHtml(Genre)
        val Director = "Directed by  : " + "<b>" + model.director + "</b>"
        holder.movieDirector.text = Html.fromHtml(Director)
        Glide.with(context).load(model.posterUrl).into(holder.moviePoster)
        if (sqliteData!!.checkFav(model.imdbId)) {
            holder.movieLike.setImageResource(R.drawable.ic_fav)
        } else {
            holder.movieLike.setImageResource(R.drawable.ic_nonfav)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, movie_display_activity::class.java)
            intent.putExtra("movieTitle", arrayList[position].title)
            intent.putExtra("moviePoster", arrayList[position].posterUrl)
            intent.putExtra("movieRating", arrayList[position].rating)
            intent.putExtra("movieYear", arrayList[position].year)
            intent.putExtra("movieSummary", arrayList[position].summary)
            intent.putExtra("movieWriter", arrayList[position].writers)
            intent.putExtra("movieDirector", arrayList[position].director)
            intent.putExtra("movieCast", arrayList[position].cast)
            context.startActivity(intent)
        }
        holder.movieLike.setOnClickListener {
            if (sqliteData!!.checkFav(model.imdbId)) {
                sqliteData!!.removeFromFav(model.imdbId)
                Toast.makeText(context, "removed from favourite", Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            } else {
                val b = sqliteData!!.insertIntoFav(model)
                if (b) {
                    Toast.makeText(context, "Favourite added", Toast.LENGTH_SHORT).show()
                }
                notifyDataSetChanged()
            }
        }
        holder.movieDelete.setOnClickListener { delete_movie.deleteMovie(model.imdbId) }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moviePoster: ImageView
        var movieLike: ImageView
        var movieDelete: ImageView
        var movieTitle: TextView
        var movieYear: TextView
        var movieGenre: TextView
        var movieDirector: TextView

        init {
            moviePoster = itemView.findViewById(R.id.fragment1_item_moviePoster)
            movieTitle = itemView.findViewById(R.id.fragment1_item_movieTitle)
            movieYear = itemView.findViewById(R.id.fragment1_item_movieYear)
            movieGenre = itemView.findViewById(R.id.fragment1_item_movieGenre)
            movieDirector = itemView.findViewById(R.id.fragment1_item_movieDirector)
            movieLike = itemView.findViewById(R.id.fragment1_item_movieFav)
            movieDelete = itemView.findViewById(R.id.fragment1_item_movieDelete)
        }
    }

    init {
        this.arrayList = arrayList
        this.context = context
        this.delete_movie = delete_movie
    }
}