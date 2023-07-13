package com.example.movielistapp

import retrofit2.http.GET
import com.example.movielistapp.model.MovieListModel
import retrofit2.Call

interface APIinterface {
    @get:GET("1.json")
    val movieList: Call<MovieListModel?>?

    @get:GET("2.json")
    val movieList2: Call<MovieListModel?>?
}