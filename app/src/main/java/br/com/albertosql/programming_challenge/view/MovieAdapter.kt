package br.com.albertosql.programming_challenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.albertosql.programming_challenge.R
import br.com.albertosql.programming_challenge.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

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
            tvTitle.text = item.title

            if (item.poster != "N/A") {
                Glide.with(itemView.context).load(item.poster).centerCrop().into(ivMovie)
            } else {
                Glide.with(itemView.context)
                    .load("https://image.flaticon.com/icons/png/512/1695/1695213.png").centerCrop()
                    .into(ivMovie)
            }

        }
    }

}