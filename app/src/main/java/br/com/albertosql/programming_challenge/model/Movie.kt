package br.com.albertosql.programming_challenge.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    var title: String?,
    @SerializedName("Year")
    var year: String?,
    @SerializedName("Rated")
    var rated: String?,
    @SerializedName("Released")
    var released: String?,
    @SerializedName("Runtime")
    var runtime: String?,
    @SerializedName("Genre")
    var genre: String?,
    @SerializedName("Plot")
    var plot: String?,
    @SerializedName("Poster")
    var poster: String?,
    @SerializedName("Ratings")
    var ratings: List<Rating>?,
    @SerializedName("imdbID")
    var imdbID: String?,
)