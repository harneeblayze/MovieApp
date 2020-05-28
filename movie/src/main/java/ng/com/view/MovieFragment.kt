package ng.com.view


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie.*
import ng.com.core.di.ViewModelFactory
import ng.com.core.utils.Result

import ng.com.module.R
import ng.com.module.adapter.MoviesAdapter
import ng.com.module.network.response.Movie
import ng.com.module.viewmodels.MoviesViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), MoviesAdapter.ClickListener  {


    private lateinit var movieViewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProviders.of(this, viewmodelFactory).get(MoviesViewModel::class.java)


        initRecyclerView()
        observeData()
    }

    private fun initRecyclerView() {
        moviesAdapter = MoviesAdapter(this)
        movie_recycler.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2, RecyclerView.VERTICAL, false)
        }
    }

    private fun observeData() {

        movieViewModel.getMoviesLiveData().observe(viewLifecycleOwner, Observer{ response ->

            when(response){
                is Result.Loading -> {
                    loading_bar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    loading_bar.visibility = View.INVISIBLE
                    response.data?.results?.let { moviesAdapter.submitlist(it) }
                }
                is Result.Error -> {
                    loading_bar.visibility = View.INVISIBLE
                    Toast.makeText(activity, response.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }


    override fun onItemSelected(movie: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("movieId", movie.id.toString())
        startActivity(intent)
    }

}
