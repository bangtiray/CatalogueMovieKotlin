package com.bangtiray.tmdb.ui.fragment.upcoming


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
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
import com.bangtiray.tmdb.ui.fragment.conpre.Contract
import com.google.gson.Gson
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UpcomingFragment : Fragment(), Contract.View, SwipeRefreshLayout.OnRefreshListener {


    @Inject
    lateinit var presenter: Contract.Presenter
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.recyclerview)
    lateinit var recyclerview: RecyclerView
    private var lists: MutableList<ResultsItem> = mutableListOf()
    @BindView(R.id.resultMovie)
    lateinit var status: TextView
    @BindView(R.id.swipe_refresh)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var unbinder: Unbinder

    private var currentPage = 1
    private var totalPages = 1
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }


    private fun setList() {
        movieAdapter = MovieAdapter(lists) {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.MOVIES, Gson().toJson(it))
            startActivity(intent)
        }

        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.adapter = movieAdapter

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
        val view = inflater.inflate(R.layout.recycler, container, false)
        unbinder = ButterKnife.bind(this, view)
        setList()
        setupListScrollListener()
        swipeRefreshLayout.setOnRefreshListener(this)
        return view
    }

    private fun setupListScrollListener() {
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val totalItems = layoutManager.itemCount
                val visibleItems = layoutManager.childCount
                val pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition()

                if (pastVisibleItems + visibleItems >= totalItems) {
                    if (currentPage < totalPages) currentPage++
                    startRefreshing()
                }
            }


        })
    }

    private fun startRefreshing() {
        if (swipeRefreshLayout.isRefreshing) return
        swipeRefreshLayout.isRefreshing = true
        presenter.loadData("upcoming", currentPage)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        status.visibility = View.GONE
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        presenter.attach(this, activity!!)
        presenter.subscribe()
        startRefreshing()

    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
        presenter.unsubscribe()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun loadData(totalPage: Int, list: List<ResultsItem>?) {

        totalPages=totalPage
        var items: MutableList<ResultsItem> = list!!.toMutableList()
        if (currentPage > 1) {
            lists.addAll(items)
        }
        else {
            lists.addAll(items)
        }
        movieAdapter.notifyDataSetChanged()
        stopRefrehing()
    }

    override fun showError(localizedMessage: String) {
        Toast.makeText(activity, localizedMessage, Toast.LENGTH_LONG).show()

    }

    override fun onRefresh() {
        currentPage = 1
        totalPages = 1
        stopRefrehing()
        startRefreshing()
    }

    private fun stopRefrehing() {
        if (swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = false
    }
}
