package ng.com.module.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ng.com.module.R
import ng.com.module.network.response.Movie
import ng.com.module.network.response.MovieListResponse

class MoviesAdapter (private val clickListerner: ClickListener): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){


    var moviesList: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MoviesViewHolder(view, clickListerner)
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        val movie = moviesList[position]
        holder.bind(movie)
    }

    fun submitlist(movies: List<Movie>){
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }


    inner class MoviesViewHolder(view: View, private val interaction: ClickListener): RecyclerView.ViewHolder(view){

        val posterImageView = view.findViewById<ImageView>(R.id.movie_poster)

        fun bind(movie: Movie){

            itemView.setOnClickListener {
                interaction.onItemSelected(movie)
            }

            Glide.with(posterImageView.context)
                .load(movie.getFormattedPosterPath())
                .into(posterImageView)

        }
    }

    interface ClickListener {

        fun onItemSelected(movie: Movie)
    }
}