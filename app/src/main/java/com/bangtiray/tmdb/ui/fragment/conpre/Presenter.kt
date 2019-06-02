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
    override fun loadData(cat: String) {
        view.showProgress()
        api = ApiServiceInterface.create()
        when (cat) {
            "now" -> nowPlaying()
            "upcoming" -> upcoming()
        }


    }

    private fun upcoming() {
        var subscribe = api.upcoming(Localization.country).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.loadData(it.results)
            }, {
                view.hideProgress()
                view.showError(it.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    private fun nowPlaying() {

        var subscribe = api.nowPlaying(Localization.country).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.loadData(it.results)
            }, {
                view.hideProgress()
                view.showError(it.localizedMessage)
            })

        subscriptions.add(subscribe)
    }


    override fun searchData(search: String) {
        view.showProgress()
        api = ApiServiceInterface.create()
        var subscribe = api.searchMovie(search, Localization.country).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.loadData(it.results)
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