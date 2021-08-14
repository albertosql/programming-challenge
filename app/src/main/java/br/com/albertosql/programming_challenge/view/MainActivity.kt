package br.com.albertosql.programming_challenge.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.api.model.MovieApiResult
import br.com.albertosql.programming_challenge.api.model.MovieService
import br.com.albertosql.programming_challenge.domain.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val apiUrl = "https://www.omdbapi.com/"

class MainActivity : AppCompatActivity() {

    var listOfMovies: MutableList<Movie> = mutableListOf()
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initReciclerView()

        val tvSearch = findViewById<TextView>(R.id.tvSearch)
        val btnSearchMovie = findViewById<Button>(R.id.button_search)
        btnSearchMovie.setOnClickListener {
            retrofitSearch(tvSearch.text.toString())
        }

    }

    private fun initReciclerView() {

        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        movieAdapter = MovieAdapter(listOfMovies)
        rvMovies.apply {
            hasFixedSize()
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
        }
    }

    fun retrofitSearch(searchText: String) {
        //Toast.makeText(this,searchText,2).show()
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val resultSearch = retrofit.create(MovieService::class.java).search(searchText)
        resultSearch.enqueue(object : Callback<MovieApiResult> {

            override fun onFailure(call: Call<MovieApiResult>, t: Throwable) {
                Log.e("Search", "Error: ${t}")
            }

            override fun onResponse(
                call: Call<MovieApiResult>,
                response: Response<MovieApiResult>
            ) {
                resetList()
                val allSearch = response.body()?.apiResult

                allSearch?.let {
                    for (movie in allSearch) {
                        if (movie.title != null && movie.poster != null) {
                            listOfMovies.add(movie)
                        }
                        Log.i("Response : ", "$movie")
                    }
//                    recyclerView_mainActivity.adapter?.notifyDataSetChanged()
                    refreshRecyclerView()
                }
            }
        })
    }

    fun resetList() {
        listOfMovies.clear()
    }

    fun refreshRecyclerView() {
        movieAdapter.notifyDataSetChanged()
    }

}