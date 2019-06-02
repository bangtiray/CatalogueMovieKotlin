package com.bangtiray.tmdb.ui.fragment.home

import android.content.Context
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bangtiray.tmdb.R
import com.bangtiray.tmdb.models.ResultsItem
import com.bangtiray.tmdb.util.Constant
import com.bangtiray.tmdb.util.DateTime
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.google.gson.Gson
import android.content.Intent
import com.bangtiray.tmdb.ui.detail.DetailMovieActivity


class HomeSlidingAdapter(private val context: Context, private val urls: MutableList<ResultsItem>) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return urls.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false)!!

        val imageView = imageLayout
            .findViewById(R.id.image) as ImageView
        val movieTitle = imageLayout.findViewById(R.id.movieTitle) as TextView

        movieTitle.text =
            urls[position].title + " [ Release : " + DateTime.getLongDate(urls[position].releaseDate.toString()) + " ]"
        Glide.with(context)
            .load(Constant.BASE_IMAGE + "w500" + urls[position].backdropPath)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.noimage)
                    .fitCenter()
            )
            .into(imageView)
        imageView.setOnClickListener {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.MOVIES, Gson().toJson(urls[position]))
            context.startActivity(intent)
        }
        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }


}