package com.bangtiray.tmdb.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bangtiray.tmdb.R
import com.bangtiray.tmdb.models.ResultsItem
import com.google.gson.Gson





class DetailMovieActivity : AppCompatActivity() {

    @BindView(R.id.movieId)
    lateinit var movieId: TextView
    private val gson = Gson()
    private lateinit var resultsItem: ResultsItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        ButterKnife.bind(this)
        val intent = intent.getStringExtra(MOVIES)
        resultsItem = gson.fromJson(intent, ResultsItem::class.java)
        movieId.text=resultsItem.title
        //movieId.text= intent.getStringExtra("id")

    }

    companion object {
        val MOVIES = "movies"
    }
}
