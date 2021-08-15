package br.com.albertosql.programming_challenge.model

import com.google.gson.annotations.SerializedName

data class MovieApiResult(
    @SerializedName("Search")
    val apiResult: List<Movie>
)