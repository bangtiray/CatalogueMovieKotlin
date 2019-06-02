package com.bangtiray.tmdb.ui.fragment.search


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
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
class SearchFragment : Fragment(), Contract.View {


    @Inject
    lateinit var presenter: Contract.Presenter
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.recyclerview)
    lateinit var recyclerview: RecyclerView
    private var lists: MutableList<ResultsItem> = mutableListOf()
    @BindView(R.id.resultMovie)
    @JvmField var status: TextView? = null
    private lateinit var unbinder: Unbinder

    private lateinit var movieAdapter: MovieAdapter

    private var searchKey = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        status?.text = activity?.resources?.getString(R.string.please)
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
        status?.text = activity?.resources?.getString(R.string.please)
        setList()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.INVISIBLE
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        presenter.attach(this, activity!!)
        presenter.subscribe()
        try {
            searchKey = arguments!!.getString(SEARCHKEY)
            if (searchKey != "") {
                presenter.searchData(searchKey)
            } else {
                status?.text = activity?.resources?.getString(R.string.please)
            }

        } catch (ex: Exception) {
            status?.text = ex.printStackTrace().toString()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
        presenter.unsubscribe()
    }

    companion object {
        val SEARCHKEY = "searchkey"
    }

    override fun showProgress() {
        status?.text = activity?.resources?.getString(R.string.loading)
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun loadData(list: List<ResultsItem>?) {
        if (list?.size == 0) {
            lists.clear()
            status?.text = "keyword " + searchKey + " " + activity?.resources?.getString(R.string.notfound)
        } else {
            status?.text = list?.size.toString() + " Results from Keyword"
            lists.clear()
            lists.addAll(list!!.toMutableList())
            movieAdapter.notifyDataSetChanged()
        }
    }

    override fun showError(localizedMessage: String) {
        Toast.makeText(activity, localizedMessage, Toast.LENGTH_LONG).show()

    }

}
