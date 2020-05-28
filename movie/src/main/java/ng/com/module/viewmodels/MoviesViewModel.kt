package ng.com.module.viewmodels

import androidx.lifecycle.ViewModel
import ng.com.module.repositories.MoviesRepository
import javax.inject.Inject

class MoviesViewModel @Inject constructor (private val repository: MoviesRepository): ViewModel(){


    init {

        repository.getMovies()
    }

    fun getMoviesLiveData() = repository.moviesLiveData


    override fun onCleared() {
        super.onCleared()
        repository.clearDisposable()
    }


}