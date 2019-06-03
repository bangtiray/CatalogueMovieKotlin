package com.bangtiray.tmdb.ui.fragment.conpre

import android.content.Context
import com.bangtiray.tmdb.api.ApiServiceInterface
import com.bangtiray.tmdb.util.Localization
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Presenter : Contract.Presenter {

    private lateinit var api: ApiServiceInterface
    private val subscriptions = CompositeDisposable()
    private lateinit var view: Contract.View
    private lateinit var context: Context
    override fun loadData(cat: String, page :Int) {
        view.showProgress()
        api = ApiServiceInterface.create()
        when (cat) {
            "now" -> nowPlaying(page)
            "upcoming" -> upcoming(page)
        }


    }

    private fun upcoming(page: Int) {
        var subscribe = api.upcoming(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.loadData(it.totalPages, it.results)
            }, {
                view.hideProgress()
                view.showError(it.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    private fun nowPlaying(page: Int) {

        var subscribe = api.nowPlaying(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.loadData(it.totalPages, it.results)
            }, {
                view.hideProgress()
                view.showError(it.localizedMessage)
            })

        subscriptions.add(subscribe)
    }


    override fun searchData(search: String, page: Int) {
        view.showProgress()
        api = ApiServiceInterface.create()
        var subscribe = api.searchMovie(search, page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.loadData(it.totalPages, it.results)
            }, {
                view.hideProgress()
                view.showError(it.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: Contract.View, context: Context) {
        this.view = view
        this.context = context
    }

}