package ng.com.module.network

import io.reactivex.Single
import ng.com.module.network.response.MovieDetailResponse
import ng.com.module.network.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    fun getMovieList(): Single<MovieListResponse>

    @GET("3/movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: String): Single<MovieDetailResponse>
}