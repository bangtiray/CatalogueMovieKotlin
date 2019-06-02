package com.bangtiray.tmdb.ui.fragment.home


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder

import com.bangtiray.tmdb.R
import com.bangtiray.tmdb.adapter.MovieAdapter
import com.bangtiray.tmdb.di.component.DaggerFragmentComponent
import com.bangtiray.tmdb.di.modul.FragmentModule
import com.bangtiray.tmdb.models.ResultsItem
import com.bangtiray.tmdb.ui.detail.DetailMovieActivity
import com.google.gson.Gson
import com.viewpagerindicator.CirclePageIndicator
import java.util.*
import javax.inject.Inject

class HomeFragment : Fragment(), HomeContract.View {


    @Inject
    lateinit var presenter: HomeContract.Presenter
    @BindView(R.id.pager)
    @JvmField  var mPager: ViewPager? =null
    @BindView(R.id.indicator)
    lateinit var indicator: CirclePageIndicator
    @BindView(R.id.progress_circular)
    lateinit var sliderProgress: ProgressBar
    @BindView(R.id.recyclerview)
    lateinit var rvUpcoming: RecyclerView
    private var listUpcoming: MutableList<ResultsItem> = mutableListOf()

    private lateinit var unbinder: Unbinder

    private lateinit var movieAdapter: MovieAdapter

    private var currentPage = 0
    private var NUM_PAGES = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val component = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        unbinder = ButterKnife.bind(this, view)
        upcomingSet()
        return view
    }

    private fun upcomingSet() {
        movieAdapter = MovieAdapter(listUpcoming) {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.MOVIES, Gson().toJson(it))
            startActivity(intent)
        }

        rvUpcoming.layoutManager = LinearLayoutManager(activity)
        rvUpcoming.itemAnimator = DefaultItemAnimator()
        rvUpcoming.adapter = movieAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        presenter.attach(this, activity!!)
        presenter.subscribe()
        presenter.loadSlider() //slider

        presenter.loadUpcoming()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
        presenter.unsubscribe()
    }

    override fun showProgress() {
        sliderProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        sliderProgress.visibility = View.INVISIBLE
    }


    override fun loadSlider(list: List<ResultsItem>?) {
        mPager?.adapter = HomeSlidingAdapter(activity!!, list!!.toMutableList())

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density
        indicator.radius = 5 * density

        NUM_PAGES = list.size

        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 2000, 2000)

        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    override fun loadUpcoming(list: List<ResultsItem>?) {
        listUpcoming.clear()
        listUpcoming.addAll(list!!.toMutableList())
        movieAdapter.notifyDataSetChanged()
    }

    override fun showError(localizedMessage: String) {
        Toast.makeText(activity, localizedMessage, Toast.LENGTH_LONG).show()
    }
}
