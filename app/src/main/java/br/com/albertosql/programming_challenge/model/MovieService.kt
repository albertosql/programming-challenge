package br.com.albertosql.programming_challenge.model

import br.com.albertosql.programming_challenge.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/")
    fun search(
        @Query("s") searchText: String,
        @Query("apikey") ombd_api_key: String = Constants.API_KEY_OMDB
    ): Call<MovieApiResult>

    @GET("/")
    fun getMovie(
        @Query("i") movieId: String,
        @Query("apikey") ombd_api_key: String = Constants.API_KEY_OMDB
    ): Call<Movie>
}