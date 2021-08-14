package br.com.albertosql.programming_challenge.api.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY_OMDB = "ebfbb79"

interface MovieService {
    @GET("/")
    fun search(
        @Query("s") searchText: String,
        @Query("apikey") ombd_api_key: String = API_KEY_OMDB
    ): Call<MovieApiResult>

    @GET("/")
    fun getMovie(
        @Query("i") movieId: String,
        @Query("apikey") ombd_api_key: String = API_KEY_OMDB
    ): Call<MovieApiResult>
}