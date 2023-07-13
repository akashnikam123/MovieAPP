package com.example.movielistapp.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Movie(
    @field:Expose @field:SerializedName("IMDBID") var imdbId: String,
    @field:Expose @field:SerializedName("Title") var title: String,
    @field:Expose @field:SerializedName(
        "Year"
    ) var year: String,
    @field:Expose @field:SerializedName("Summary") var summary: String,
    @field:Expose @field:SerializedName(
        "Short Summary"
    ) var shortSummary: String,
    @field:Expose @field:SerializedName("Genres") var genres: String,
    @field:Expose @field:SerializedName(
        "Runtime"
    ) var runtime: String,
    @field:Expose @field:SerializedName("YouTube Trailer") var trailerUrl: String,
    @field:Expose @field:SerializedName(
        "Rating"
    ) var rating: String,
    @field:Expose @field:SerializedName("Movie Poster") var posterUrl: String,
    @field:Expose @field:SerializedName(
        "Director"
    ) var director: String,
    @field:Expose @field:SerializedName(
        "Writers"
    ) var writers: String,
    @field:Expose @field:SerializedName("Cast") var cast: String
)