package com.bangtiray.tmdb.models

import com.google.gson.annotations.SerializedName


data class NowPlayingModel (

    @SerializedName("dates")
    var dates: Dates? = null,

    @SerializedName("page")
    var page: Int = 0,

    @SerializedName("total_pages")
    var totalPages: Int = 0,

    @SerializedName("results")
    var results: List<ResultsItem>? = null,

    @SerializedName("total_results")
    var totalResults: Int = 0
)


class Dates {

    @SerializedName("maximum")
    var maximum: String? = null

    @SerializedName("minimum")
    var minimum: String? = null

}

data class ResultsItem (

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("video")
    var isVideo: Boolean = false,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble(),

    @SerializedName("popularity")
    var popularity: Double = 0.toDouble(),

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("adult")
    var isAdult: Boolean = false,

    @SerializedName("vote_count")
    var voteCount: Int = 0

)


data class UpcomingModel (

    @SerializedName("dates")
    var dates: Dates? = null,

    @SerializedName("page")
    var page: Int = 0,

    @SerializedName("total_pages")
    var totalPages: Int = 0,

    @SerializedName("results")
    var results: List<ResultsItem>? = null,

    @SerializedName("total_results")
    var totalResults: Int = 0


)