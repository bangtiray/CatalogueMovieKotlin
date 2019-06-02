package com.bangtiray.tmdb.ui.fragment.home

import android.content.Context
import com.bangtiray.tmdb.api.ApiServiceInterface
import com.bangtiray.tmdb.util.Localization
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter : HomeContract.Presenter {


    private lateinit var api: ApiServiceInterface
    private val subscriptions = CompositeDisposable()
    private lateinit var view: HomeContract.View
    private lateinit var context: Context

    override fun loadSlider() {
        view.showProgress()
        api = ApiServiceInterface.create()
        var subscribe=api.nowPlaying(Localization.country).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.loadSlider(it.results)
            },{
                view.hideProgress()
                view.showError(it.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun loadUpcoming() {
        api = ApiServiceInterface.create()
        var subscribe=api.upcoming(Localization.country).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view.loadUpcoming(it.results)
            },{
                view.showError(it.localizedMessage)
            })

        subscriptions.add(subscribe)

    }
    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: HomeContract.View, context: Context) {
        this.view=view
        this.context=context
    }
}