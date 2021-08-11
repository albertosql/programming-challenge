package br.com.albertosql.programming_challenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.domain.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvMovies)
        val titanic = Movie(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
            "Titanic",
            "Um artista pobre e uma jovem rica se conhecem e se apaixonam na " +
                    "fatídica jornada do Titanic, em 1912. Embora esteja noiva do arrogante" +
                    " herdeiro de uma siderúrgica, a jovem desafia sua família e amigos em " +
                    "busca do verdadeiro amor."


        )
        val movies = listOf(titanic, titanic, titanic, titanic, titanic)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(movies)
    }
}