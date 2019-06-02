package com.bangtiray.tmdb.ui.fragment.home

import com.bangtiray.tmdb.BaseContract
import com.bangtiray.tmdb.models.ResultsItem

class HomeContract {
    interface View : BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun loadSlider(list: List<ResultsItem>?)
        fun loadUpcoming(list: List<ResultsItem>?)
        fun showError(localizedMessage: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadSlider()
        fun loadUpcoming()
    }


}