package ng.com.module.repositories

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ng.com.module.network.MovieApi
import ng.com.module.network.response.MovieListResponse
import ng.com.core.utils.Result
import ng.com.module.network.response.MovieDetailResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieApi: MovieApi
){

     val moviesLiveData = MutableLiveData<Result<MovieListResponse>>()
     val movieDetailLiveData = MutableLiveData<Result<MovieDetailResponse>>()
    val compositeDisposable = CompositeDisposable()


    fun getMovies(){

        moviesLiveData.value = Result.Loading()

        compositeDisposable.add(
        movieApi.getMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
                moviesLiveData.value = Result.Success(response)
            },{
                moviesLiveData.value = Result.Error(it.localizedMessage)
            }))
    }


    fun getMovieDetail(movieId:String) {

        movieDetailLiveData.value = Result.Loading()

        compositeDisposable.add(
            movieApi.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieDetailLiveData.value = Result.Success(it)
                },{
                    movieDetailLiveData.value = Result.Error(it.localizedMessage)
                })
        )
    }


    fun clearDisposable(){
        compositeDisposable.clear()
    }

}