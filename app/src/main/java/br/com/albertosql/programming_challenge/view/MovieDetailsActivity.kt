package br.com.albertosql.programming_challenge.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.model.Movie
import br.com.albertosql.programming_challenge.model.MovieService
import br.com.albertosql.programming_challenge.model.RetrofitService
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var retrofit: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        retrofit = RetrofitService()
        val idMovie = intent.extras?.get("idMovie").toString()
        retrofitSearchDetails(idMovie)

    }

    private fun retrofitSearchDetails(idMovie: String) {

        val resultSearchDetails =
            retrofit.initRetrofit().create(MovieService::class.java).getMovie(idMovie)
        resultSearchDetails.enqueue(object : Callback<Movie> {

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("Search", "Error: $t")
            }

            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                fillTextViews(response.body())
            }
        })
    }

    private fun fillTextViews(movie: Movie?) {
        tvMovie.text = movie?.title
        tvYear.text = movie?.year
        tvPlot.text = movie?.plot
        tvGenre.text = movie?.genre
        tvRealise.text = movie?.released
        tvTime.text = movie?.runtime

        if (movie?.poster != "N/A") {
            Glide.with(baseContext).load(movie?.poster).centerCrop().into(ivPoster)
        } else {
            Glide.with(baseContext)
                .load("https://image.flaticon.com/icons/png/512/1695/1695213.png").centerCrop()
                .into(ivPoster)
        }

        val ratings = movie?.ratings
        ratings?.let {
            for (rating in ratings) {
                when (rating.source) {
                    "Internet Movie Database" -> tvInternet.text = rating.value
                    "Rotten Tomatoes" -> tvTomatoes.text = rating.value
                    "Metacritic" -> tvMetacritic.text = rating.value
                }
            }
        }
    }

}