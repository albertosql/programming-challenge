package br.com.albertosql.programming_challenge.api.model

import br.com.albertosql.programming_challenge.domain.Movie
import com.google.gson.annotations.SerializedName

data class MovieApiResult(
    @SerializedName("Search")
    val apiResult: List<Movie>
)