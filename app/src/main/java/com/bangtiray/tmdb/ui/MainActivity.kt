package com.bangtiray.tmdb.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.bangtiray.tmdb.R
import com.bangtiray.tmdb.ui.fragment.home.HomeFragment
import com.bangtiray.tmdb.ui.fragment.playnow.PlaynowFragment
import com.bangtiray.tmdb.ui.fragment.profile.ProfileFragment
import com.bangtiray.tmdb.ui.fragment.upcoming.UpcomingFragment
import com.roughike.bottombar.BottomBar
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import com.bangtiray.tmdb.ui.fragment.search.SearchFragment


class MainActivity : AppCompatActivity() {
    @BindView(R.id.framelayout)
    @JvmField
    var frameLayout: FrameLayout? = null

    @BindView(R.id.bottombar)
    @JvmField
    var bottomBar: BottomBar? = null

    @BindView(R.id.headerTitle)
    @JvmField
    var titleHeader: TextView? = null

    @BindView(R.id.linear1)
    @JvmField
    var linear1: LinearLayout? = null

    @BindView(R.id.linear2)
    @JvmField
    var linear2: LinearLayout? = null

    @BindView(R.id.searchEdit)
    @JvmField
    var searchEdit: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        bottomSet(savedInstanceState)
        searchEdit?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    performSearch()
                    hideKeyboard(v)
                    return true
                }
                return false
            }
        })
    }


    private fun bottomSet(savedInstanceState: Bundle?) {
        for (i in 0 until bottomBar?.tabCount!!) {
            bottomBar?.getTabAtPosition(i)?.gravity = Gravity.CENTER_VERTICAL
        }
        bottomBar?.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.home -> {
                    replaceFragment(HomeFragment(), savedInstanceState)
                    linear1?.visibility = View.VISIBLE
                    linear2?.visibility = View.GONE
                    titleHeader?.text = resources.getString(R.string.home)

                }
                R.id.playnow -> {

                    replaceFragment(PlaynowFragment(), savedInstanceState)
                    linear1?.visibility = View.VISIBLE
                    linear2?.visibility = View.GONE
                    titleHeader?.text = resources.getString(R.string.playnow)
                }
                R.id.upcomming -> {
                    replaceFragment(UpcomingFragment(), savedInstanceState)
                    linear1?.visibility = View.VISIBLE
                    linear2?.visibility = View.GONE
                    titleHeader?.text = resources.getString(R.string.upcomming)
                }

                R.id.search -> {

                    replaceFragment(SearchFragment(), savedInstanceState)
                    searchEdit?.text?.clear()
                    searchEdit?.requestFocus()
                    linear1?.visibility = View.GONE
                    linear2?.visibility = View.VISIBLE
                    titleHeader?.text = resources.getString(R.string.general)

                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framelayout, fragment, fragment::class.java.simpleName)
            transaction.commit()
        }
    }

    private fun performSearch() {
        val data = Bundle()
        data.putString(SearchFragment.SEARCHKEY, searchEdit?.text.toString())
        val searchFragment = SearchFragment()
        searchFragment.arguments = data
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayout, searchFragment, searchFragment::class.java.simpleName)
            .commit()

    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
