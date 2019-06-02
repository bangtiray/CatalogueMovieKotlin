package com.bangtiray.tmdb.models

import com.google.gson.annotations.SerializedName

data class Responses(

    @SerializedName("id")
    var movieid:Int?,
    @SerializedName("backdrop_path")
    var backdropPath:String?=null,
    @SerializedName("poster_path")
    var posterPath:String?=null,
    @SerializedName("budget")
    var projectBudget:Int,
    @SerializedName("overview")
    var movieOverview:String
)