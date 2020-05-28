package ng.com.module.network.response

import com.google.gson.annotations.SerializedName
import ng.com.core.utils.AppConstants

data class MovieListResponse(
    @SerializedName("page") val page: Long? = null,
    @SerializedName("total_results") val totalResults: Long? = null,
    @SerializedName("total_pages") val totalPages: Long? = null,
    @SerializedName("results") val results: List<Movie>? = null
)


data class Movie (
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("vote_count") val voteCount: Long? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("id") val id: Long? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("genre_ids") val genreIDS: List<Long>? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null
) {

    fun getFormattedPosterPath(): String? {
        if (posterPath != null && !posterPath!!.startsWith("http")) {
            posterPath = String.format(AppConstants.IMAGE_URL, posterPath)
        }
        return posterPath
    }
}