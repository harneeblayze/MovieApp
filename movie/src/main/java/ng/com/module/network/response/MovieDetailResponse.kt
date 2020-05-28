package ng.com.module.network.response

import com.google.gson.annotations.SerializedName
import ng.com.core.utils.AppConstants


data class MovieDetailResponse (
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("poster_path")var posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null
) {

    fun getFormattedPosterPath(): String? {
        if (posterPath != null && !posterPath!!.startsWith("http")) {
            posterPath = String.format(AppConstants.IMAGE_URL, posterPath)
        }
        return posterPath
    }
}

