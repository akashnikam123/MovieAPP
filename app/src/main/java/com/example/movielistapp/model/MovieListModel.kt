package com.example.movielistapp.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class MovieListModel {
    @SerializedName("Movie List")
    @Expose
    var movies: List<Movie>? = null
}