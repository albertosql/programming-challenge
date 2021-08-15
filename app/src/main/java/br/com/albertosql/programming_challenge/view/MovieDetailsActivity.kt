package br.com.albertosql.programming_challenge.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.api.model.MovieService
import br.com.albertosql.programming_challenge.domain.Movie
import br.com.albertosql.programming_challenge.util.Constants
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        var idMovie = intent.extras?.get("idMovie").toString()
        retrofitSearchDetails(idMovie)
    }

    private fun retrofitSearchDetails(idMovie: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val resultSearchDetails = retrofit.create(MovieService::class.java).getMovie(idMovie)
        resultSearchDetails.enqueue(object : Callback<Movie> {

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("Search", "Error: ${t}")
            }

            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                val ivPosterResult = response.body()?.poster
                val tvMovieResult = response.body()?.title
                val tvYearResult = response.body()?.year
                val tvPlotResult = response.body()?.plot
                val tvGenreResult = response.body()?.genre
                val tvReleaseResult = response.body()?.released
                val tvTimeResult = response.body()?.runtime
                val tvTomatoesResult = response.body()?.imdbRating
                val tvInternetResult = response.body()?.imdbRating
                val tvMetacriticResult = response.body()?.imdbRating

                val ivPoster = findViewById<ImageView>(R.id.ivPoster)
                val tvMovie = findViewById<TextView>(R.id.tvMovie)
                val tvYear = findViewById<TextView>(R.id.tvYear)
                val tvPlot = findViewById<TextView>(R.id.tvPlot)
                val tvGenre = findViewById<TextView>(R.id.tvGenre)
                val tvRelease = findViewById<TextView>(R.id.tvRealise)
                val tvTime = findViewById<TextView>(R.id.tvTime)
                val tvTomatoes = findViewById<TextView>(R.id.tvTomatoes)
                val tvInternet = findViewById<TextView>(R.id.tvInternet)
                val tvMetacritic = findViewById<TextView>(R.id.tvMetacritic)

                Glide.with(baseContext).load(ivPosterResult).centerCrop().into(ivPoster)
                tvMovie.text = tvMovieResult
                tvYear.text = tvYearResult
                tvPlot.text = tvPlotResult
                tvGenre.text = tvGenreResult
                tvRelease.text = tvReleaseResult
                tvTime.text = tvTimeResult
                tvTomatoes.text = tvTomatoesResult
                tvInternet.text = tvInternetResult
                tvMetacritic.text = tvMetacriticResult

            }

        })
    }
}


//pacote util