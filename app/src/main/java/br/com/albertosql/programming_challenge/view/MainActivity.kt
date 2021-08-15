package br.com.albertosql.programming_challenge.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.model.Movie
import br.com.albertosql.programming_challenge.model.MovieApiResult
import br.com.albertosql.programming_challenge.model.MovieService
import br.com.albertosql.programming_challenge.model.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var listOfMovies: MutableList<Movie> = mutableListOf()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var retrofit: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = RetrofitService()
        initRecyclerView()
        button_search.setOnClickListener {
            retrofitSearch(tvSearch.text.toString())
        }
    }

    private fun initRecyclerView() {

        movieAdapter = MovieAdapter(listOfMovies)
        rvMovies.apply {
            hasFixedSize()
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
        }
        movieAdapter.onItemClick = {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("idMovie", it.imdbID)
            startActivity(intent)
        }
    }

    private fun retrofitSearch(searchText: String) {

        val resultSearch =
            retrofit.initRetrofit().create(MovieService::class.java).search(searchText)
        resultSearch.enqueue(object : Callback<MovieApiResult> {

            override fun onFailure(call: Call<MovieApiResult>, t: Throwable) {
                Log.e("Search", "Error: $t")
            }

            override fun onResponse(
                call: Call<MovieApiResult>,
                response: Response<MovieApiResult>
            ) {
                resetList()
                val movies = response.body()?.apiResult

                movies?.let {
                    for (movie in movies) {
                        if (movie.title != null && movie.poster != null)
                            listOfMovies.add(movie)
                    }

                    refreshRecyclerView()
                }
            }
        })
    }

    fun resetList() {
        listOfMovies.clear()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshRecyclerView() {
        movieAdapter.notifyDataSetChanged()
    }

}