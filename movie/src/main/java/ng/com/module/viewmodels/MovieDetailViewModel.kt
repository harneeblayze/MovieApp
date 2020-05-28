package ng.com.module.viewmodels

import androidx.lifecycle.ViewModel
import ng.com.module.repositories.MoviesRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor (private val repository: MoviesRepository): ViewModel(){


    fun getMovieDetail(movieId:String) = repository.getMovieDetail(movieId)

    fun observeMovieDetails() = repository.movieDetailLiveData

    override fun onCleared() {
        super.onCleared()
        repository.clearDisposable()
    }

}