package ng.com.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_detail.*
import ng.com.core.di.ViewModelFactory
import ng.com.core.utils.Result
import ng.com.module.R
import ng.com.module.network.response.MovieDetailResponse
import ng.com.module.viewmodels.MovieDetailViewModel
import ng.com.module.viewmodels.MoviesViewModel
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieDetailViewModel
    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.title = "Movie Detail"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        movieViewModel = ViewModelProviders.of(this, viewmodelFactory).get(MovieDetailViewModel::class.java)

        val movieId = intent.getStringExtra("movieId")
        movieId?.let {
            movieViewModel.getMovieDetail(it)
        }

        observeMovieDetails()
    }

    private fun observeMovieDetails() {

        movieViewModel.observeMovieDetails().observe(this, Observer { response ->
            when(response){
                is Result.Loading ->{
                    hideMovieDetails()
                }
                is Result.Success ->{
                    showMovieDetails(response)

                }
                is Result.Error ->{
                    hideMovieDetails()
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    fun hideMovieDetails(){
        progress_bar.visibility = View.VISIBLE
        movie_details.visibility = View.INVISIBLE
    }

    fun showMovieDetails(response: Result<MovieDetailResponse>){
        progress_bar.visibility = View.INVISIBLE
        movie_details.visibility = View.VISIBLE

        Glide.with(this)
            .load(response.data?.getFormattedPosterPath())
            .into(background_image)

        movie_name.text = response.data?.originalTitle
        rating.text = response.data?.voteAverage.toString()
        release_date.text = response.data?.releaseDate
        overview.text = response.data?.overview

    }


}
