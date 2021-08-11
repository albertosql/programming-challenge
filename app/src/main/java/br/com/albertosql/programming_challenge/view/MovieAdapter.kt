package br.com.albertosql.programming_challenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.domain.Movie

class MovieAdapter(
    private val items: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Movie) = with(itemView) {
            val ivMovie = findViewById<ImageView>(R.id.ivMovie)
            val tvTitle = findViewById<TextView>(R.id.tvTitle)
            val tvDescriptionMovie = findViewById<TextView>(R.id.tvDescriptionMovie)

            //TODO: LOAD IMAGE WITH GLADE

            tvTitle.text = item.title
            tvDescriptionMovie.text = item.description
        }
    }
}