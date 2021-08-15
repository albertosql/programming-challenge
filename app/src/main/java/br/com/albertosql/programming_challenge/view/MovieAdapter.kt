package br.com.albertosql.programming_challenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.domain.Movie
import com.bumptech.glide.Glide

class MovieAdapter(
    private val items: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Movie) = with(itemView) {
            val ivMovie = findViewById<ImageView>(R.id.ivMovie)
            val tvTitle = findViewById<TextView>(R.id.tvTitle)

            Glide.with(itemView.context).load(item.poster).centerCrop().into(ivMovie)
            tvTitle.text = item.title
        }
    }
}