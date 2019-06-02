package com.bangtiray.tmdb.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.bangtiray.tmdb.R
import com.bangtiray.tmdb.models.ResultsItem
import com.bangtiray.tmdb.util.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView

class MovieAdapter(private val list: MutableList<ResultsItem>, private val listener: (ResultsItem) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_list, p0, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItems(list[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var image: PorterShapeImageView = view.findViewById<View>(R.id.image) as PorterShapeImageView
        private var title: TextView = view.findViewById<View>(R.id.titleMovie) as TextView
        private var voteCount: TextView = view.findViewById(R.id.voteCount)
        private var btnDetail: TextView = view.findViewById(R.id.btnDetail)
        private var ratingBar: RatingBar = view.findViewById(R.id.ratingbar)
        private var popularity: TextView = view.findViewById(R.id.popularity)
        fun bindItems(list: ResultsItem, listener: (ResultsItem) -> Unit) {
            title.text = list.title
            ratingBar.rating = list.voteAverage.toFloat()
            voteCount.text = list.voteAverage.toString()
            popularity.text="Popularity : " + list.popularity.toInt()
            Glide.with(itemView)
                .load(Constant.BASE_IMAGE + "w500" + list.posterPath)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.noimage)
                        .fitCenter()
                )
                .into(image)

            btnDetail.setOnClickListener { listener(list) }
        }
    }

}